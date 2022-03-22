package io.gitee.oauth.context;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

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
    public static String getUserDetails() {
        return (String) getAuthentication().getPrincipal();
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

    /**
     * 获取用户登录IP
     */
    public static String getRemoteAddress() {
        return ((OAuth2AuthenticationDetails) getAuthentication().getDetails()).getRemoteAddress();
    }

    /**
     * 获取请求参数
     */
    public static OAuth2Request getRequestParams() {
        return ((OAuth2Authentication) getAuthentication()).getOAuth2Request();
    }

    /**
     * 获取客户端appid
     */
    public static String getClientId() {
        return getRequestParams().getClientId();
    }

    /**
     * 获取Token值
     */
    public static String getTokenValue() {
        Object details = getAuthentication().getDetails();
        return ((OAuth2AuthenticationDetails) details).getTokenValue();
    }

    /**
     * 获取Token类型
     */
    public static String getTokenType() {
        Object details = getAuthentication().getDetails();
        return ((OAuth2AuthenticationDetails) details).getTokenType();
    }
}
