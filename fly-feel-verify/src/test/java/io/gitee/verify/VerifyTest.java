package io.gitee.verify;

import io.gitee.define.entity.Verify;
import io.gitee.test.SpringBootTestParent;
import io.gitee.verify.config.VerifyAutoConfiguration;
import io.gitee.verify.core.Captcha;
import io.gitee.verify.core.SvgCaptcha;
import org.junit.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Import;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author Cikaros
 * @date 2022/3/25
 * @since v1.0
 */
@SpringBootConfiguration
@Import(VerifyAutoConfiguration.class)
public class VerifyTest extends SpringBootTestParent {

    @Test
    public void run() throws Exception {
        Captcha captcha = Captcha.Builder.create(SvgCaptcha.class)
                .build();
        Verify verify = captcha.build();
        File tempFile = File.createTempFile("verify_", "." + captcha.contentType());
        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            verify.write(out);
        }
        System.out.println(verify);
        System.out.println(tempFile.getAbsoluteFile());
        String[] cmd = new String[]{
                "cmd",
                "/C",
                "start " + tempFile.toURI()
        };
        Runtime.getRuntime().exec(cmd);
    }

}
