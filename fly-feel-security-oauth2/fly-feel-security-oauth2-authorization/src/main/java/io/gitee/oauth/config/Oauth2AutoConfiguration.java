package io.gitee.oauth.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Cikaros
 * @date 2022/3/21
 * @since v1.0
 */
@Configuration(proxyBeanMethods = false)
@ComponentScan({
        "io.gitee.oauth.advice",
        "io.gitee.oauth.service.impl"
})
public class Oauth2AutoConfiguration {
}
