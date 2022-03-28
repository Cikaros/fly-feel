package io.gitee.core.config.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.List;

/**
 * 请求日志配置
 *
 * @author Cikaros
 * @date 2022/3/27
 * @since v1.0
 */
@ConfigurationProperties("fly-feel.request-log")
public class RequestLoggerProperties {

    private List<String> excludePaths = Arrays.asList("**.html", "**.js", "**.css", "**.png", "**.jpg", "**.gif", "**.svg");

    private Long timeout = 1000L;

    public List<String> getExcludePaths() {
        return excludePaths;
    }

    public void setExcludePaths(List<String> excludePaths) {
        this.excludePaths = excludePaths;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }
}
