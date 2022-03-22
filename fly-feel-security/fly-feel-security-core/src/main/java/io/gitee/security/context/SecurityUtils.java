package io.gitee.security.context;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

/**
 * @author Cikaros
 * @date 2022/3/21
 * @since v1.0
 */
public class SecurityUtils {

    /**
     * 获取用户
     */
    public static Object getUserDetails() {
        return getAuthentication().getPrincipal();
    }

    /**
     * 获取当前权限列表
     */
    public static Collection<? extends GrantedAuthority> getAuthorities() {
        return getAuthentication().getAuthorities();
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return getContext().getAuthentication();
    }

    /**
     * 获取SecurityContext
     */
    public static SecurityContext getContext() {
        return SecurityContextHolder.getContext();
    }

    /**
     * 清除SecurityContext
     */
    public static void clearContext() {
        getContext().setAuthentication(null);
        SecurityContextHolder.clearContext();
    }
}
