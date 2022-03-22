package io.gitee.oauth.config.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collection;

/**
 * Xss配置
 *
 * @author Cikaros
 * @date 2022/3/19
 * @since v1.0
 */
@ConfigurationProperties(prefix = "spring.security.xss")
public class XssConfigProperties {

    public Boolean enabled;

    public Collection<String> urlExcludes;

    public Collection<String> urlPatterns;

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setUrlExcludes(Collection<String> urlExcludes) {
        this.urlExcludes = urlExcludes;
    }

    public void setUrlPatterns(Collection<String> urlPatterns) {
        this.urlPatterns = urlPatterns;
    }
}
