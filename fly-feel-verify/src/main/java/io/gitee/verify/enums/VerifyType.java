package io.gitee.verify.enums;

/**
 * 验证码类型
 *
 * @author Cikaros
 * @date 2022/4/26
 */
public enum VerifyType {
    /**
     * 默认（数字字母混合）
     */
    DEFAULT,
    /**
     * 纯数字
     */
    ONLY_DIGIT,
    /**
     * 纯字母
     */
    ONLY_LITTER,
    /**
     * 大写字母
     */
    ONLY_UPPER,
    /**
     * 纯小写字母
     */
    ONLY_LOWER,
    /**
     * 中文
     */
    ONLY_CHINESE;
}
