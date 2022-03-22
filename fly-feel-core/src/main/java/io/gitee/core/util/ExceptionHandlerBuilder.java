package io.gitee.core.util;

import io.gitee.core.entity.model.RestResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * 全局异常处理构造器
 *
 * @author Cikaros
 * @date 2022/3/20
 * @since v1.0
 */
public class ExceptionHandlerBuilder {

    private String view;

    private Map<String, ?> params;

    private RestResult restResult;

    private final HttpServletRequest request;

    private ExceptionHandlerBuilder(HttpServletRequest request) {
        this.request = request;
    }

    public static ExceptionHandlerBuilder getInstance(HttpServletRequest request) {
        return new ExceptionHandlerBuilder(request);
    }

    public ExceptionHandlerBuilder page(String view) {
        this.view = view;
        return this;
    }

    public ExceptionHandlerBuilder page(String view, Map<String, ?> params) {
        this.view = view;
        this.params = params;
        return this;
    }

    public ExceptionHandlerBuilder json(RestResult restResult) {
        this.restResult = restResult;
        return this;
    }

    public Object build() {
        Assert.notNull(request, "The argument request must be non null!");
        Assert.notNull(view, "The argument view must be non null!");
        Assert.notNull(restResult, "The argument restResult must be non null!");
        if (Objects.isNull(params)) {
            params = Collections.singletonMap("msg", restResult.getMessage());
        }
        String accept = request.getHeader(HttpHeaders.ACCEPT) + "";
        if (accept.contains(MediaType.APPLICATION_JSON_VALUE)) {
            return restResult;
        }
        ModelAndView modelAndView = new ModelAndView(view);
        modelAndView.addObject("status", restResult.getStatusCodeValue());
        modelAndView.addAllObjects(this.params);
        return modelAndView;
    }

}
