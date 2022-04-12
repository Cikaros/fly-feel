package io.gitee.core.util;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;

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
    public static ApplicationContext getWebApplicationContext(ServletRequest request) {
        Object context = getApplication(request).getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        return context instanceof ApplicationContext ? (ApplicationContext) context : null;
    }

    /**
     * 获取Application对象
     */
    public static ServletContext getApplication(ServletRequest request) {
        return request.getServletContext();
    }
}
