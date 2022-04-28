package io.gitee.core.config;

import io.gitee.core.config.props.RequestLoggerProperties;
import io.gitee.core.filter.RepeatableFilter;
import io.gitee.core.filter.RequestFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import javax.servlet.Filter;

/**
 * 核心模块的自动装配
 *
 * @author Cikaros
 * @date 2021/7/7
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({ServerProperties.class, RequestLoggerProperties.class})
@ComponentScan("io.gitee.core.*")
@ServletComponentScan("io.gitee.core.listener")
public class CoreAutoConfiguration {

    @Value("${server.error.path:${error.path:/error}}")
    protected String errorPath;

    /**
     * Request包装过滤器
     */
    @Bean
    public FilterRegistrationBean<Filter> repeatableFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RepeatableFilter(errorPath));
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registrationBean;
    }

    /**
     * Request日志过滤器
     */
    @Bean
    public FilterRegistrationBean<Filter> RequestFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE + 1);
        return registrationBean;
    }


}
