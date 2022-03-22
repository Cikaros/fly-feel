package io.gitee.define.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * 自定义GrantedAuthority
 *
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
public class SecurityAuthority extends Authority implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return this.getCode();
    }
}
