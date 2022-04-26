package io.gitee.verify.core;

import io.gitee.verify.enums.VerifyType;

import java.awt.*;

/**
 * Png验证码对象
 *
 * @author Cikaros
 * @date 2022/4/26
 */
public class PngCaptcha extends Captcha{
    @Override
    public String contentType() {
        return "png";
    }

    public PngCaptcha() {
    }

    public PngCaptcha(int width, int height, int length, VerifyType type, Font font) {
        super(width, height, length, type, font);
    }
}
