package io.gitee.define.enums;

/**
 * Oauth授权模式
 *
 * @author Cikaros
 * @date 2022/3/19
 * @since v1.0
 */
public enum GrantType {
    /**
     * 隐式模式
     */
    IMPLICIT,
    /**
     * 密码模式
     */
    PASSWORD,
    /**
     * 授权码模式
     */
    AUTHORIZATION_CODE,
    /**
     * 刷新token模式
     */
    REFRESH_TOKEN,
    /**
     * 客户端模式
     */
    CLIENT_CREDENTIALS
}
