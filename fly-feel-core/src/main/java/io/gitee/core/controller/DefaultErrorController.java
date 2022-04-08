package io.gitee.core.controller;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 自定义异常统一入口
 *
 * @author Cikaros
 * @date 2022/3/20
 * @since v1.0
 */
@Controller
@RequestMapping({"${server.error.path:${error.path:/error}}"})
public class DefaultErrorController extends AbstractErrorController implements ErrorController {

    private final Logger log = Logger.getLogger(DefaultErrorController.class);

    @Value("${server.error.path:${error.path:/error}}")
    private String errorPath;

    private final ErrorProperties errorProperties;

    public DefaultErrorController(ErrorAttributes errorAttributes, List<ErrorViewResolver> errorViewResolvers, ServerProperties serverProperties) {
        super(errorAttributes, errorViewResolvers);
        this.errorProperties = serverProperties.getError();
    }

    @RequestMapping(
            produces = {"text/html"}
    )
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        Exception exception = (Exception) request.getAttribute(DefaultErrorController.class.getName());
        if (Objects.nonNull(exception)) {
            throw this.getCoreException(exception);
        }
        HttpStatus status = this.getStatus(request);
        Map<String, Object> model = Collections.unmodifiableMap(this.getErrorAttributes(request, this.getErrorAttributeOptions(request, MediaType.TEXT_HTML)));
        response.setStatus(status.value());
        ModelAndView modelAndView = this.resolveErrorView(request, response, status, model);
        return modelAndView != null ? modelAndView : new ModelAndView("5xx", model);
    }

    @RequestMapping(produces = {"application/json"})
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) throws Throwable {
        Exception exception = (Exception) request.getAttribute(DefaultErrorController.class.getName());
        if (Objects.nonNull(exception)) {
            throw this.getCoreException(exception);
        }
        HttpStatus status = this.getStatus(request);
        if (status == HttpStatus.NO_CONTENT) {
            return new ResponseEntity<>(status);
        } else {
            Map<String, Object> body = this.getErrorAttributes(request, this.getErrorAttributeOptions(request, MediaType.ALL));
            return new ResponseEntity<>(body, status);
        }
    }

    protected ErrorAttributeOptions getErrorAttributeOptions(HttpServletRequest request, MediaType mediaType) {
        ErrorAttributeOptions options = ErrorAttributeOptions.defaults();
        if (this.errorProperties.isIncludeException()) {
            options = options.including(ErrorAttributeOptions.Include.EXCEPTION);
        }

        if (this.isIncludeStackTrace(request, mediaType)) {
            options = options.including(ErrorAttributeOptions.Include.STACK_TRACE);
        }

        if (this.isIncludeMessage(request)) {
            options = options.including(ErrorAttributeOptions.Include.MESSAGE);
        }

        if (this.isIncludeBindingErrors(request)) {
            options = options.including(ErrorAttributeOptions.Include.BINDING_ERRORS);
        }

        return options;
    }

    protected boolean isIncludeStackTrace(HttpServletRequest request, MediaType produces) {
        switch (this.getErrorProperties().getIncludeStacktrace()) {
            case ALWAYS:
                return true;
            case ON_PARAM:
                return this.getTraceParameter(request);
            default:
                return false;
        }
    }

    protected boolean isIncludeMessage(HttpServletRequest request) {
        switch (this.getErrorProperties().getIncludeMessage()) {
            case ALWAYS:
                return true;
            case ON_PARAM:
                return this.getMessageParameter(request);
            default:
                return false;
        }
    }

    protected boolean isIncludeBindingErrors(HttpServletRequest request) {
        switch (this.getErrorProperties().getIncludeBindingErrors()) {
            case ALWAYS:
                return true;
            case ON_PARAM:
                return this.getErrorsParameter(request);
            default:
                return false;
        }
    }

    protected ErrorProperties getErrorProperties() {
        return this.errorProperties;
    }

    @Override
    public String getErrorPath() {
        return errorPath;
    }

    private Throwable getCoreException(@NonNull Throwable parent) {
        Throwable cause = parent;
        Throwable core;
        do {
            core = cause;
            cause = core.getCause();
        }
        while (Objects.nonNull(cause) && core != cause);
        log.warn(core);
        return core;
    }
}