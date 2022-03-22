package io.gitee.security.handler;

import io.gitee.core.controller.DefaultErrorController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证异常处理
 *
 * @author Cikaros
 * @date 2021/7/31
 */
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {

    private static final Logger log = LoggerFactory.getLogger(AuthExceptionEntryPoint.class);

    private final String errorPath;

    public AuthExceptionEntryPoint(String errorPath) {
        this.errorPath = errorPath;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.debug("认证异常处理:{}", e.getMessage());
        request.setAttribute(DefaultErrorController.class.getName(), e);
        request.getRequestDispatcher(errorPath).forward(request, response);
    }

}
