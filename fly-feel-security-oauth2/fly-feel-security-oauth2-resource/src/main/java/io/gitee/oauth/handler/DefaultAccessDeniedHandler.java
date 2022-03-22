package io.gitee.oauth.handler;

import io.gitee.core.controller.DefaultErrorController;
import io.gitee.oauth.context.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 无权访问处理器
 *
 * @author Cikaros
 * @date 2022/3/20
 * @since v1.0
 */

public class DefaultAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger log = LoggerFactory.getLogger(DefaultAccessDeniedHandler.class);

    private final String errorPath;

    public DefaultAccessDeniedHandler(String errorPath) {
        this.errorPath = errorPath;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        log.debug("用户({})无权访问地址[{}]!", SecurityUtils.getUserDetails(), request.getRequestURL());
        request.setAttribute(DefaultErrorController.class.getName(), e);
        request.getRequestDispatcher(errorPath).forward(request, response);
    }

}
