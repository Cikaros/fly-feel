## 权限的使用

权限的具体实现在`common-framework-module`、`common-integration-module`、
`common-permission-module`、`common-resource-module`这几个模块中。

具体的使用步骤只需在Controller的接口方法上写上`@PreAuthorize("@ss.hasPermission(null)")`即可实现接口权限的鉴定。

若想获取用户和权限信息，则可调用工具类`SecurityUtils`的相应方法即可实现。具体实现代码如下：

```java
/**
 * 安全服务工具类
 *
 * @author Cikaros
 * @date 2021/7/8
 */
public class SecurityUtils {

    /**
     * 获取用户
     */
    public static SecurityUserDetails getUserDetails() {
        try {
            return (SecurityUserDetails) getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new FrameworkException("获取用户信息异常");
        }
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
    public static Long getDepartmentId() {
        return getUserDetails().getDepartmentId();
    }

    /**
     * 获取顶层组织Id
     */
    public static Long getTopDepartmentId() {
        return getUserDetails().getTopId();
    }

    /**
     * 获取用户昵称
     */
    public static String getNickName() {
        return getUserDetails().getNickName();
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
     * 获取用户登录IP
     */
    public static String getRemoteAddress() {
        if (getAuthentication().getDetails() instanceof OAuth2AuthenticationDetails) {
            return ((OAuth2AuthenticationDetails) getAuthentication().getDetails()).getRemoteAddress();
        }
        throw new FrameworkException("获取用户信息异常");
    }

    /**
     * 获取请求参数
     */
    public static OAuth2Request getRequestParams() {
        if (getAuthentication() instanceof OAuth2Authentication) {
            return ((OAuth2Authentication) getAuthentication()).getOAuth2Request();
        }
        throw new FrameworkException("获取用户信息异常");
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
        if (details instanceof OAuth2AuthenticationDetails) {
            return ((OAuth2AuthenticationDetails) details).getTokenValue();
        }
        throw new FrameworkException("获取用户信息异常");
    }

    /**
     * 获取Token类型
     */
    public static String getTokenType() {
        Object details = getAuthentication().getDetails();
        if (details instanceof OAuth2AuthenticationDetails) {
            return ((OAuth2AuthenticationDetails) details).getTokenValue();
        }
        throw new FrameworkException("获取用户信息异常");
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
```

JavaDoc文档地址：[https://cikaros.gitee.io/cikaros-solution/javadoc/com/shibo/common/framework/security/utils/SecurityUtils.html](https://cikaros.gitee.io/cikaros-solution/javadoc/com/shibo/common/framework/security/utils/SecurityUtils.html)
