package io.gitee.define.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 定义业务接口实现自动装配类
 *
 * @author Cikaros
 * @date 2022/3/22
 * @since v1.0
 */
@Configuration(proxyBeanMethods = false)
@ComponentScan("io.gitee.define.service.impl")
public class DefineServiceAutoConfiguration {
}
