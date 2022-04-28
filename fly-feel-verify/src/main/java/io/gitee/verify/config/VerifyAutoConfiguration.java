package io.gitee.verify.config;

import io.gitee.verify.core.Captcha;
import io.gitee.verify.core.GifCaptcha;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 验证码模块自动加载类
 *
 * @author Cikaros
 * @date 2022/3/23
 * @since v1.0
 */
@Configuration
public class VerifyAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(Captcha.class)
    public Captcha captcha() throws Exception {
        return Captcha.Builder.create(GifCaptcha.class).build();
    }

}
