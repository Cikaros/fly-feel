package io.gitee.verify;

import io.gitee.define.entity.Verify;
import io.gitee.test.SpringBootTestParent;
import io.gitee.verify.config.VerifyAutoConfiguration;
import io.gitee.verify.core.*;
import io.gitee.verify.utils.Randoms;
import org.junit.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Import;

import java.awt.*;
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
        Captcha captcha = Captcha.Builder.create(GifCaptcha.class)
                .build();
        Verify verify = captcha.build();
        File tempFile = File.createTempFile("verify_", "." + captcha.contentType());
        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            verify.write(out);
        }
        System.out.println(verify);
        String[] cmd = new String[]{
                "cmd",
                "/C",
                "start " + tempFile.toURI()
        };
        Runtime.getRuntime().exec(cmd);
    }

}
