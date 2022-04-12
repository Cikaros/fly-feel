package io.gitee.message.config;

import io.gitee.message.NettyBootstrapRunner;
import io.gitee.message.config.props.MessageProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Message自动装配
 *
 * @author Cikaros
 * @date 2022/4/6
 * @since v1.0
 */
@Configuration(proxyBeanMethods = false)
@Import(NettyBootstrapRunner.class)
@EnableConfigurationProperties(MessageProperties.class)
@ComponentScan("io.gitee.message.handler")
public class MessageAutoConfiguration {

}
