package io.gitee.core.filter;

import io.gitee.core.controller.DefaultErrorController;
import io.gitee.core.http.RepeatedlyHttpRequestWrapper;
import org.springframework.http.MediaType;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 过滤并构建可重复读取的HttpRequest对象
 *
 * @author Cikaros
 * @date 2021/12/19
 */
public class RepeatableFilter implements Filter {

    private final String errorPath;

    public RepeatableFilter(String errorPath) {
        this.errorPath = errorPath;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ServletRequest requestWrapper = null;
        String contentType = request.getContentType() + "";
        if (request instanceof HttpServletRequest
                && contentType.contains(MediaType.APPLICATION_JSON_VALUE)) {
            requestWrapper = new RepeatedlyHttpRequestWrapper((HttpServletRequest) request, response);
        }
        try {
            if (null == requestWrapper) {
                chain.doFilter(request, response);
            } else {
                chain.doFilter(requestWrapper, response);
            }
        } catch (Exception e) {
            request.setAttribute(DefaultErrorController.class.getName(), e);
            request.getRequestDispatcher(errorPath).forward(request, response);
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
