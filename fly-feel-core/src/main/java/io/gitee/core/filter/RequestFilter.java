package io.gitee.core.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.gitee.core.config.props.RequestLoggerProperties;
import io.gitee.core.util.HttpUtils;
import io.gitee.core.util.ServletUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Assert;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * 请求日志
 *
 * @author Cikaros
 * @date 2022/4/12
 */
public class RequestFilter implements Filter {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final Deque<Long> startTime = new ArrayDeque<>();

    private ObjectMapper mapper;

    private RequestLoggerProperties config;

    private final AntPathMatcher matcher;

    public RequestFilter() {
        matcher = new AntPathMatcher("");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (req instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) req;
            try {
                if (Objects.isNull(this.mapper)) {
                    ApplicationContext applicationContext = ServletUtils.getWebApplicationContext(request);
                    Assert.notNull(applicationContext, "Cannot get ApplicationContext!");
                    this.mapper = applicationContext.getBean(ObjectMapper.class);
                    this.config = applicationContext.getBean(RequestLoggerProperties.class);
                }
                if (isMatches(request)) {
                    printLog(request);
                }
            } catch (IOException e) {
                log.warn("The request log output exception!", e);
            }
            chain.doFilter(request, response);
            if (isMatches(request)) {
                long timeCost = System.currentTimeMillis() - startTime.pop();
                if (timeCost <= config.getTimeout()) log.debug("请求处理结束. 处理耗时: {}", timeCost);
                else log.warn("请求处理结束. 处理耗时: {}", timeCost);
            }
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private boolean isMatches(HttpServletRequest request) {
        String uri = request.getRequestURI();
        for (String excludePath : config.getExcludePaths()) {
            if (matcher.matchStart(excludePath, uri)) {
                return false;
            }
        }
        return true;
    }

    private void printLog(HttpServletRequest request) throws IOException {
        startTime.push(System.currentTimeMillis());
        String uri = request.getRequestURI();
        String contentType = Objects.isNull(request.getContentType()) ? "*/*" : request.getContentType();
        log.debug("用户访问地址: {}, 来路地址: {}", uri, request.getRemoteAddr());
        Enumeration<String> enums = request.getHeaderNames();
        while (enums.hasMoreElements()) {
            String name = enums.nextElement();
            log.debug("请求头: {} = {}", name, request.getHeader(name));
        }
        if (contentType.contains(MediaType.APPLICATION_JSON_VALUE)) {
            log.debug("请求参数: {}", HttpUtils.getReduceBodyString(request));
        } else {
            Map<String, String[]> params = request.getParameterMap();
            for (String key : params.keySet()) {
                log.debug("请求参数: {} = {}", key, mapper.writeValueAsString(params.get(key)));
            }
        }
    }


}
