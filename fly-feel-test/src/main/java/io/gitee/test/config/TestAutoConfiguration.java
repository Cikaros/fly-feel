package io.gitee.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * 测试工具的自动载入
 *
 * @author Cikaros
 * @date 2022/3/20
 * @since v1.0
 */
@Configuration(proxyBeanMethods = false)
public class TestAutoConfiguration {

    @Bean
    public PodamFactory podamFactory() {
        return new PodamFactoryImpl();
    }
}
