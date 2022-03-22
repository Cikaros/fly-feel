package io.gitee.define.entity;

import java.util.Collection;
import java.util.Objects;

/**
 * 自定义角色
 *
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
public class SecurityRole extends Role {

    private Collection<SecurityAuthority> authorities;

    public Collection<SecurityAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<SecurityAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SecurityRole that = (SecurityRole) o;
        return Objects.equals(authorities, that.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), authorities);
    }

    @Override
    public String toString() {
        return "SecurityRole{" +
                "authorities=" + authorities +
                '}';
    }
}
