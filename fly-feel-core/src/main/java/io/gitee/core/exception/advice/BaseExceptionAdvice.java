package io.gitee.core.exception.advice;

import io.gitee.core.exception.BaseException;
import io.gitee.core.entity.model.RestResult;
import io.gitee.core.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ResourceBundle;

@RestControllerAdvice
public class BaseExceptionAdvice {

    protected final Logger log = LoggerFactory.getLogger(BaseService.class);

    protected static ResourceBundle i18n = ResourceBundle.getBundle("i18n/Exception");

    @ExceptionHandler(BaseException.class)
    public RestResult baseExceptionHandler(BaseException e) {
        log.error(e.getMessage());
        return RestResult.error(e.getMessage());
    }
}
