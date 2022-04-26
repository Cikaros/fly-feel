package io.gitee.verify.core;

import io.gitee.verify.enums.VerifyType;

import java.awt.*;

/**
 * JPG验证码对象
 *
 * @author Cikaros
 * @date 2022/4/26
 */
public class JpgCaptcha extends Captcha{
    public JpgCaptcha() {
    }

    public JpgCaptcha(int width, int height, int length, VerifyType type, Font font) {
        super(width, height, length, type, font);
    }

    @Override
    public String contentType() {
        return "jpg";
    }
}
