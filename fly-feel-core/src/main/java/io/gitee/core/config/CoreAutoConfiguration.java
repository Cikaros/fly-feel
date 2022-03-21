package io.gitee.core.config;

import io.gitee.core.filter.RepeatableFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import javax.servlet.Filter;

/**
 * 核心模块的自动装配
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({ServerProperties.class})
@ComponentScan("io.gitee.core.*")
public class CoreAutoConfiguration {

    @Value("${server.error.path:${error.path:/error}}")
    protected String errorPath;

    @Bean
    public FilterRegistrationBean<Filter> repeatableFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RepeatableFilter(errorPath));
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(Ordered.LOWEST_PRECEDENCE);
        return registrationBean;
    }

}
