/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.gitee.openfeign.named;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.MapPropertySource;
import org.springframework.lang.NonNull;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Creates a set of child contexts that allows a set of Specifications to define the beans
 * in each child context.
 * <p>
 * Ported from spring-cloud-netflix FeignClientFactory and SpringClientFactory
 *
 * @param <C> specification
 * @author Spencer Gibb
 * @author Dave Syer
 */
public abstract class NamedContextFactory<C extends NamedContextFactory.Specification> implements DisposableBean, ApplicationContextAware {

    private final String propertySourceName;

    private final String propertyName;

    private final Map<String, AnnotationConfigApplicationContext> contexts = new ConcurrentHashMap<>();

    private final Map<String, C> configurations = new ConcurrentHashMap<>();

    private ApplicationContext parent;

    private final Class<?> defaultConfigType;

    public NamedContextFactory(Class<?> defaultConfigType, String propertySourceName,
                               String propertyName) {
        this.defaultConfigType = defaultConfigType;
        this.propertySourceName = propertySourceName;
        this.propertyName = propertyName;
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext parent) throws BeansException {
        this.parent = parent;
    }

    public void setConfigurations(List<C> configurations) {
        for (C client : configurations) {
            this.configurations.put(client.getName(), client);
        }
    }

    public Set<String> getContextNames() {
        return new HashSet<>(this.contexts.keySet());
    }

    @Override
    public void destroy() {
        Collection<AnnotationConfigApplicationContext> values = this.contexts.values();
        for (AnnotationConfigApplicationContext context : values) {
            // This can fail, but it never throws an exception (you see stack traces
            // logged as WARN).
            context.close();
        }
        this.contexts.clear();
    }

    protected AnnotationConfigApplicationContext getContext(String name) {
        if (!this.contexts.containsKey(name)) {
            synchronized (this.contexts) {
                if (!this.contexts.containsKey(name)) {
                    this.contexts.put(name, createContext(name));
                }
            }
        }
        return this.contexts.get(name);
    }

    protected AnnotationConfigApplicationContext createContext(String name) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        if (this.configurations.containsKey(name)) {
            for (Class<?> configuration : this.configurations.get(name)
                    .getConfiguration()) {
                context.register(configuration);
            }
        }
        for (Map.Entry<String, C> entry : this.configurations.entrySet()) {
            if (entry.getKey().startsWith("default.")) {
                for (Class<?> configuration : entry.getValue().getConfiguration()) {
                    context.register(configuration);
                }
            }
        }
        context.register(PropertyPlaceholderAutoConfiguration.class,
                this.defaultConfigType);
        context.getEnvironment().getPropertySources().addFirst(new MapPropertySource(
                this.propertySourceName,
                Collections.singletonMap(this.propertyName, name)));
        if (this.parent != null) {
            // Uses Environment from parent as well as beans
            context.setParent(this.parent);
            // jdk11 issue
            // https://github.com/spring-cloud/spring-cloud-netflix/issues/3101
            context.setClassLoader(this.parent.getClassLoader());
        }
        context.setDisplayName(generateDisplayName(name));
        context.refresh();
        return context;
    }

    protected String generateDisplayName(String name) {
        return this.getClass().getSimpleName() + "-" + name;
    }

    public <T> T getInstance(String name, Class<T> type) {
        AnnotationConfigApplicationContext context = getContext(name);
        if (BeanFactoryUtils.beanNamesForTypeIncludingAncestors(context,
                type).length > 0) {
            return context.getBean(type);
        }
        return null;
    }

    public <T> ObjectProvider<T> getLazyProvider(String name, Class<T> type) {
        return new ClientFactoryObjectProvider<>(this, name, type);
    }

    public <T> ObjectProvider<T> getProvider(String name, Class<T> type) {
        AnnotationConfigApplicationContext context = getContext(name);
        return context.getBeanProvider(type);
    }

    public <T> T getInstance(String name, Class<?> clazz, Class<?>... generics) {
        ResolvableType type = ResolvableType.forClassWithGenerics(clazz, generics);
        return getInstance(name, type);
    }

    @SuppressWarnings("unchecked")
    public <T> T getInstance(String name, ResolvableType type) {
        AnnotationConfigApplicationContext context = getContext(name);
        String[] beanNames = BeanFactoryUtils.beanNamesForTypeIncludingAncestors(context,
                type);
        if (beanNames.length > 0) {
            for (String beanName : beanNames) {
                if (context.isTypeMatch(beanName, type)) {
                    return (T) context.getBean(beanName);
                }
            }
        }
        return null;
    }

    public <T> Map<String, T> getInstances(String name, Class<T> type) {
        AnnotationConfigApplicationContext context = getContext(name);
        if (BeanFactoryUtils.beanNamesForTypeIncludingAncestors(context,
                type).length > 0) {
            return BeanFactoryUtils.beansOfTypeIncludingAncestors(context, type);
        }
        return null;
    }

    /**
     * Specification with name and configuration.
     */
    public interface Specification {

        String getName();

        Class<?>[] getConfiguration();

    }

}
