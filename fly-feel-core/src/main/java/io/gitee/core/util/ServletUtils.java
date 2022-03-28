package io.gitee.core.util;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

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
     *
     * @param servletContext 全局域
     */
    public static ApplicationContext getExistingRootWebApplicationContext(ServletContext servletContext) {
        Object context = servletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        return context instanceof ApplicationContext ? (ApplicationContext) context : null;
    }
}
