package io.gitee.security.advice;

import io.gitee.core.entity.model.RestResult;
import io.gitee.core.util.ExceptionHandlerBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

/**
 * Security全局异常处理
 *
 * @author Cikaros
 * @date 2022/3/20
 * @since v1.0
 */
@ControllerAdvice
public class SecurityControllerAdvice {

    @ExceptionHandler(NoHandlerFoundException.class)
    public Object NoHandlerFoundExceptionHandler(NoHandlerFoundException e, HttpServletRequest request) {
        return ExceptionHandlerBuilder.getInstance(request)
                .page("404", Collections.singletonMap("path", e.getRequestURL()))
                .json(RestResult.newInstance(HttpStatus.NOT_FOUND, e.getMessage()))
                .build();
    }


    @ExceptionHandler(AccessDeniedException.class)
    public Object AccessDeniedExceptionHandler(AccessDeniedException e, HttpServletRequest request) {
        return ExceptionHandlerBuilder.getInstance(request)
                .page("5xx")
                .json(RestResult.newInstance(HttpStatus.UNAUTHORIZED, e.getMessage()))
                .build();
    }

    @ExceptionHandler(RuntimeException.class)
    public Object RuntimeExceptionHandler(RuntimeException e, HttpServletRequest request) {
        return ExceptionHandlerBuilder.getInstance(request)
                .page("5xx")
                .json(RestResult.newInstance(HttpStatus.BAD_REQUEST, e.getMessage()))
                .build();
    }

    @ExceptionHandler(Exception.class)
    public Object ExceptionHandler(Exception e, HttpServletRequest request) {
        return ExceptionHandlerBuilder.getInstance(request)
                .page("5xx")
                .json(RestResult.newInstance(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()))
                .build();
    }

}
