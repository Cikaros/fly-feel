package io.gitee.oauth.advice;

import io.gitee.core.entity.model.RestResult;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Cikaros
 * @date 2022/3/20
 * @since v1.0
 */
@Order(50)
@RestControllerAdvice
public class OauthControllerAdvice {


    /**
     * 未进行授权验证或认证失效
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public RestResult HttpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException ex) {
        return RestResult.newInstance(HttpStatus.UNAUTHORIZED, null, "进行授权验证或认证失效，请刷新认证或重新认证！");
    }

    /**
     * 未进行授权验证或认证失效
     */
    @ExceptionHandler(InsufficientAuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public RestResult InsufficientAuthenticationExceptionHandler(InsufficientAuthenticationException ex) {
        return RestResult.newInstance(HttpStatus.UNAUTHORIZED, null, "进行授权验证或认证失效，请刷新认证或重新认证！");
    }

    /**
     * 授权不存在
     */
    @ExceptionHandler(InvalidGrantException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public RestResult InvalidGrantExceptionHandler(InvalidGrantException ex) {
        return RestResult.newInstance(HttpStatus.NOT_ACCEPTABLE, null, "无效的授权信息！");
    }

    /**
     * 账户异常(禁用、锁定、过期)
     */
    @ExceptionHandler({InternalAuthenticationServiceException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public RestResult InternalAuthenticationServiceExceptionHandler(InternalAuthenticationServiceException e) {
        return RestResult.newInstance(HttpStatus.UNAUTHORIZED, e.getMessage());
    }

    /**
     * 客户端不存在
     */
    @ExceptionHandler({NoSuchClientException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public RestResult NoSuchClientExceptionHandler(NoSuchClientException e) {
        return RestResult.newInstance(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
    }

    /**
     * 客户端不存在
     */
    @ExceptionHandler({ClientRegistrationException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public RestResult ClientRegistrationExceptionHandler(ClientRegistrationException e) {
        return RestResult.newInstance(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
    }


    /**
     * 没有凭证
     */
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public RestResult BadCredentialsExceptionHandler(BadCredentialsException ex) {
        return RestResult.newInstance(HttpStatus.NOT_ACCEPTABLE, null, "错误的凭证，无法访问！");
    }

    /**
     * 错误的客户端凭证
     */
    @ExceptionHandler(BadClientCredentialsException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public RestResult BadClientCredentialsExceptionHandler(BadClientCredentialsException ex) {
        return RestResult.newInstance(HttpStatus.NOT_ACCEPTABLE, null, "错误的客户端凭证，无法访问！");
    }

    /**
     * 客户端类型不支持
     */
    @ExceptionHandler(InvalidClientException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public RestResult InvalidClientExceptionHandler(InvalidClientException ex) {
        return RestResult.newInstance(HttpStatus.FORBIDDEN, null, "该客户端不支持此项服务！");
    }

    /**
     * Token已失效
     */
    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public RestResult InvalidTokenExceptionHandler(InvalidTokenException ex) {
        return RestResult.newInstance(HttpStatus.NOT_ACCEPTABLE, null, "该Token已失效！");
    }

    /**
     * 未进行授权验证或认证失效
     */
    @ExceptionHandler(OAuth2Exception.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public RestResult OAuth2ExceptionHandler(OAuth2Exception ex) {
        return RestResult.newInstance(HttpStatus.UNAUTHORIZED, null, "进行授权验证或认证失效，请刷新认证或重新认证！");
    }

}
