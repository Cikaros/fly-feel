package io.gitee.define.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Security 账户
 *
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
public class SecurityAccount extends Account implements UserDetails {

    private Collection<SecurityRole> roles;

    private Collection<SecurityAuthority> securityAuthorities;

    public Collection<SecurityAuthority> getSecurityAuthorities() {
        return securityAuthorities;
    }

    public void setSecurityAuthorities(Collection<SecurityAuthority> securityAuthorities) {
        this.securityAuthorities = securityAuthorities;
    }

    public Collection<SecurityRole> getRoles() {
        return roles;
    }

    public void setRoles(Collection<SecurityRole> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SecurityAccount that = (SecurityAccount) o;
        return Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), roles);
    }

    @Override
    public String toString() {
        return "SecurityAccount{" + "roles=" + roles + '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Stream.concat(roles.stream().flatMap(role -> role.getAuthorities().stream()), securityAuthorities.stream()).filter(SecurityAuthority::getEnable).collect(Collectors.toSet());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.getEnable();
    }
}
