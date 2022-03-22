package io.gitee.core.advice;

import io.gitee.core.entity.model.RestResult;
import io.gitee.core.exception.BaseException;
import io.gitee.core.service.BaseService;
import io.gitee.core.util.ExceptionHandlerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.ResourceBundle;

/**
 * 核心基础全局异常处理
 *
 * @author Cikaros
 * @date 2021/7/7
 */
@ControllerAdvice
public class BaseExceptionAdvice {

    protected final Logger log = LoggerFactory.getLogger(BaseService.class);

    protected static ResourceBundle i18n = ResourceBundle.getBundle("i18n/Exception");

    @ExceptionHandler(NoSuchMessageException.class)
    public Object baseExceptionHandler(NoSuchMessageException e, HttpServletRequest request) {
        return ExceptionHandlerBuilder.getInstance(request)
                .page("5xx", Collections.singletonMap("msg", e.getMessage()))
                .json(RestResult.newInstance(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()))
                .build();
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Object baseExceptionHandler(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        return ExceptionHandlerBuilder.getInstance(request)
                .page("5xx", Collections.singletonMap("msg", e.getMessage()))
                .json(RestResult.newInstance(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()))
                .build();
    }

    @ExceptionHandler(BaseException.class)
    public Object baseExceptionHandler(BaseException e, HttpServletRequest request) {
        return ExceptionHandlerBuilder.getInstance(request)
                .page("5xx", Collections.singletonMap("msg", e.getMessage()))
                .json(RestResult.newInstance(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()))
                .build();
    }
}
