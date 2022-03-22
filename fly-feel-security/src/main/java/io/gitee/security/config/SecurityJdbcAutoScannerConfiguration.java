package io.gitee.security.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 权限数据库服务自动装配类
 *
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
@Configuration(proxyBeanMethods = false)
@ComponentScan({"io.gitee.security.advice", "io.gitee.security.service.impl"})
public class SecurityJdbcAutoScannerConfiguration {
}
