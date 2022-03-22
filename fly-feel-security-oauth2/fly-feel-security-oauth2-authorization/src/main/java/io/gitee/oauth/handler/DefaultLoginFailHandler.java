package io.gitee.oauth.handler;

import io.gitee.core.controller.DefaultErrorController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DefaultLoginFailHandler implements AuthenticationFailureHandler {

    private final String errorPath;

    private static final Logger log = LoggerFactory.getLogger(DefaultLoginFailHandler.class);

    public DefaultLoginFailHandler(String errorPath) {
        this.errorPath = errorPath;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        if (e instanceof UsernameNotFoundException) {
            log.debug("用户不存在:{}", e.getMessage());
        } else if (e instanceof AccountExpiredException) {
            log.debug("账号过期:{}", e.getMessage());
        } else if (e instanceof BadCredentialsException) {
            log.debug("密码错误:{}", e.getMessage());
        } else if (e instanceof CredentialsExpiredException) {
            log.debug("密码过期:{}", e.getMessage());
        } else if (e instanceof DisabledException) {
            log.debug("账户不可用:{}", e.getMessage());
        } else if (e instanceof LockedException) {
            log.debug("账号锁定:{}", e.getMessage());
        } else if (e instanceof InternalAuthenticationServiceException) {
            log.debug("密码过期:{}", e.getMessage());
        } else {
            log.debug("未知异常:{}", e.getMessage(), e);
        }
        request.setAttribute(DefaultErrorController.class.getName(), e);
        request.getRequestDispatcher(errorPath).forward(request, response);
    }
}
