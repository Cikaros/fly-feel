package io.gitee.security.config.props;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;

/**
 * 跨域配置
 *
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
@ConfigurationProperties("spring.security.cors")
public class CorsConfigProperties extends CorsConfiguration {
}
