package io.gitee.oauth.advice;

import io.gitee.core.entity.model.RestResult;
import io.gitee.core.util.ExceptionHandlerBuilder;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

/**
 * @author Cikaros
 * @date 2022/3/20
 * @since v1.0
 */
@Order(100)
@ControllerAdvice
public class SecurityControllerAdvice {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Object NoHandlerFoundExceptionHandler(NoHandlerFoundException e, HttpServletRequest request) {
        return ExceptionHandlerBuilder.getInstance(request)
                .page("404", Collections.singletonMap("path", e.getRequestURL()))
                .json(RestResult.newInstance(HttpStatus.NOT_FOUND, e.getMessage()))
                .build();
    }


    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Object AccessDeniedExceptionHandler(AccessDeniedException e, HttpServletRequest request) {
        return ExceptionHandlerBuilder.getInstance(request)
                .page("5xx")
                .json(RestResult.newInstance(HttpStatus.UNAUTHORIZED, e.getMessage()))
                .build();
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object RuntimeExceptionHandler(RuntimeException e, HttpServletRequest request) {
        return ExceptionHandlerBuilder.getInstance(request)
                .page("5xx")
                .json(RestResult.newInstance(HttpStatus.BAD_REQUEST, e.getMessage()))
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object ExceptionHandler(Exception e, HttpServletRequest request) {
        return ExceptionHandlerBuilder.getInstance(request)
                .page("5xx")
                .json(RestResult.newInstance(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()))
                .build();
    }

}
