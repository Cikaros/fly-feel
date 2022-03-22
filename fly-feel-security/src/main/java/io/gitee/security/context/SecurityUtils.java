package io.gitee.security.context;

import io.gitee.define.entity.SecurityAccount;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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
    public static SecurityAccount getUserDetails() {
        return (SecurityAccount) getAuthentication().getPrincipal();
    }

    /**
     * 获取用户id
     */
    public static Long getUserId() {
        return getUserDetails().getId();
    }

    /**
     * 获取用户名
     */
    public static String getUsername() {
        return getUserDetails().getUsername();
    }

    /**
     * 获取组织Id
     */
    //public static Long getDepartmentId() {
    //    return getUserDetails();
    //}

    /**
     * 获取顶层组织Id
     */
    //public static Long getTopDepartmentId() {
    //    return getUserDetails().getTopId();
    //}

    /**
     * 获取用户昵称
     */
    public static String getNickname() {
        return getUserDetails().getNickname();
    }

    /**
     * 获取用户权限列表
     */
    public static Collection<? extends GrantedAuthority> getUserAuthorities() {
        return getUserDetails().getAuthorities();
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
    public static org.springframework.security.core.context.SecurityContext getContext() {
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
