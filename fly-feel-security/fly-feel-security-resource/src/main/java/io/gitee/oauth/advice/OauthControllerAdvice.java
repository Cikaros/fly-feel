package io.gitee.oauth.advice;

import io.gitee.core.entity.model.RestResult;
import io.gitee.core.service.I18nService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.common.exceptions.*;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * SecurityOauth全局异常处理
 *
 * @author Cikaros
 * @date 2022/3/20
 * @since v1.0
 */
@Order(10)
@RestControllerAdvice
public class OauthControllerAdvice {

    private I18nService i18n;

    @Autowired
    public void setI18n(I18nService i18n) {
        this.i18n = i18n;
    }

    /**
     * 未进行授权验证或认证失效
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public RestResult HttpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException ex) {
        return RestResult.newInstance(HttpStatus.UNAUTHORIZED, null, ex.getMessage());
    }

    /**
     * 没有合适的授权模式
     */
    @ExceptionHandler(InsufficientAuthenticationException.class)
    public RestResult InsufficientAuthenticationExceptionHandler(InsufficientAuthenticationException ex) {
        return RestResult.newInstance(HttpStatus.UNAUTHORIZED, null, ex.getMessage());
    }

    /**
     * 授权不存在
     */
    @ExceptionHandler(InvalidGrantException.class)
    public RestResult InvalidGrantExceptionHandler(InvalidGrantException ex) {
        return RestResult.newInstance(HttpStatus.NOT_ACCEPTABLE, null, ex.getMessage());
    }

    /**
     * 账户异常(禁用、锁定、过期)
     */
    @ExceptionHandler({InternalAuthenticationServiceException.class})
    public RestResult InternalAuthenticationServiceExceptionHandler(InternalAuthenticationServiceException e) {
        return RestResult.newInstance(HttpStatus.UNAUTHORIZED, e.getMessage());
    }

    /**
     * 客户端不存在
     */
    @ExceptionHandler({NoSuchClientException.class})
    public RestResult NoSuchClientExceptionHandler(NoSuchClientException e) {
        return RestResult.newInstance(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
    }

    /**
     * 客户端不存在
     */
    @ExceptionHandler({ClientRegistrationException.class})
    public RestResult ClientRegistrationExceptionHandler(ClientRegistrationException e) {
        return RestResult.newInstance(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
    }


    /**
     * 没有凭证
     */
    @ExceptionHandler(BadCredentialsException.class)
    public RestResult BadCredentialsExceptionHandler(BadCredentialsException ex) {
        return RestResult.newInstance(HttpStatus.NOT_ACCEPTABLE, null, ex.getMessage());
    }

    /**
     * 错误的客户端凭证
     */
    @ExceptionHandler(BadClientCredentialsException.class)
    public RestResult BadClientCredentialsExceptionHandler(BadClientCredentialsException ex) {
        return RestResult.newInstance(HttpStatus.NOT_ACCEPTABLE, null, ex.getMessage());
    }

    /**
     * 客户端类型不支持
     */
    @ExceptionHandler(InvalidClientException.class)
    public RestResult InvalidClientExceptionHandler(InvalidClientException ex) {
        return RestResult.newInstance(HttpStatus.FORBIDDEN, null, ex.getMessage());
    }

    /**
     * Token已失效
     */
    @ExceptionHandler(InvalidTokenException.class)
    public RestResult InvalidTokenExceptionHandler(InvalidTokenException ex) {
        return RestResult.newInstance(HttpStatus.NOT_ACCEPTABLE, null, ex.getMessage());
    }

    /**
     * 未进行授权验证或认证失效
     */
    @ExceptionHandler(OAuth2Exception.class)
    public RestResult OAuth2ExceptionHandler(OAuth2Exception ex) {
        return RestResult.newInstance(HttpStatus.UNAUTHORIZED, null, ex.getMessage());
    }

}
