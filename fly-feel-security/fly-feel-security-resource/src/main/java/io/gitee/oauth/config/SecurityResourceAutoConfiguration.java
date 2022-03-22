package io.gitee.oauth.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 资源服务器自动装配类
 *
 * @author Cikaros
 * @date 2022/3/22
 * @since v1.0
 */
@Configuration(proxyBeanMethods = false)
@ComponentScan("io.gitee.oauth.advice")
public class SecurityResourceAutoConfiguration {
}
