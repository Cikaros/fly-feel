package io.gitee.security.config;

import io.gitee.security.config.props.CorsConfigProperties;
import io.gitee.security.config.props.XssConfigProperties;
import io.gitee.security.filter.XssFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.DispatcherType;
import java.util.HashMap;
import java.util.Map;

/**
 * XSS攻击防范
 *
 * @author Cikaros
 * @date 2022/3/19
 * @since v1.0
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({XssConfigProperties.class, CorsConfigProperties.class})
@ComponentScan({"io.gitee.security.advice"})
public class SecurityAutoConfiguration {

    private XssConfigProperties xss;

    private CorsConfigProperties cors;

    @Autowired
    public void setCors(CorsConfigProperties cors) {
        this.cors = cors;
    }

    @Autowired
    public void setXss(XssConfigProperties xss) {
        this.xss = xss;
    }

    /**
     * Filter注册
     */
    @Bean
    @ConditionalOnProperty(name = "spring.security.xss.enabled", havingValue = "true")
    public FilterRegistrationBean<XssFilter> xssFilterRegistration() {
        FilterRegistrationBean<XssFilter> registration = new FilterRegistrationBean<>();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new XssFilter());
        registration.addUrlPatterns(String.join(",", xss.urlPatterns));
        registration.setName("xssFilter");
        registration.setOrder(FilterRegistrationBean.HIGHEST_PRECEDENCE);
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("excludes", String.join(",", xss.urlExcludes));
        initParameters.put("enabled", xss.enabled.toString());
        registration.setInitParameters(initParameters);
        return registration;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cors);
        return source;
    }
}
