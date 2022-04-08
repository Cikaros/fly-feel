package io.gitee.core.util;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet工具类
 *
 * @author Cikaros
 * @date 2022/3/27
 * @since v1.0
 */
public class ServletUtils {

    /**
     * 获取ApplicationContext
     */
    public static ApplicationContext getWebApplicationContext() {
        Object context = getApplication().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        return context instanceof ApplicationContext ? (ApplicationContext) context : null;
    }

    /**
     * 获取Request对象
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
    }

    /**
     * 获取Application对象
     */
    public static ServletContext getApplication() {
        return getRequest().getServletContext();
    }
}
