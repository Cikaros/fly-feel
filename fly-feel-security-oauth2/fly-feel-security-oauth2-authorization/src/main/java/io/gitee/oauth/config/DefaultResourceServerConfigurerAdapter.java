package io.gitee.oauth.config;

import io.gitee.oauth.handler.AuthExceptionEntryPoint;
import io.gitee.security.handler.DefaultAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

/**
 * 资源配置
 *
 * @author Cikaros
 * @date 2021/7/27
 */
@Configuration
@EnableResourceServer
@EnableConfigurationProperties(OAuth2ResourceServerProperties.class)
public class DefaultResourceServerConfigurerAdapter extends ResourceServerConfigurerAdapter {

    @Value("${server.error.path:${error.path:/error}}")
    protected String errorPath;

    private ResourceServerTokenServices tokenServices;

    private OAuth2ResourceServerProperties.Opaquetoken resourceServer;

    private TokenStore tokenStore;

    @Autowired
    public void setTokenStore(JdbcTokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    @Autowired
    public void setTokenServices(DefaultTokenServices tokenServices) {
        this.tokenServices = tokenServices;
    }

    @Autowired
    public void setResourceServer(OAuth2ResourceServerProperties resourceServer) {
        this.resourceServer = resourceServer.getOpaquetoken();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
                .resourceId(resourceServer.getClientId())
                .tokenServices(tokenServices)
                .tokenStore(tokenStore);
        resources
                .authenticationEntryPoint(new AuthExceptionEntryPoint(errorPath))
                .accessDeniedHandler(new DefaultAccessDeniedHandler(errorPath));
    }

    /**
     * anyRequest          |   匹配所有请求路径
     * access              |   SpringEl表达式结果为true时可以访问
     * anonymous           |   匿名可以访问
     * denyAll             |   用户不能访问
     * fullyAuthenticated  |   用户完全认证可以访问（非remember-me下自动登录）
     * hasAnyAuthority     |   如果有参数，参数表示权限，则其中任何一个权限可以访问
     * hasAnyRole          |   如果有参数，参数表示角色，则其中任何一个角色可以访问
     * hasAuthority        |   如果有参数，参数表示权限，则其权限可以访问
     * hasIpAddress        |   如果有参数，参数表示IP地址，如果用户IP和参数匹配，则可以访问
     * hasRole             |   如果有参数，参数表示角色，则其角色可以访问
     * permitAll           |   用户可以任意访问
     * rememberMe          |   允许通过remember-me登录的用户访问
     * authenticated       |   用户登录后可访问
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                // CSRF禁用，因为不使用session
                .csrf().disable()
                // 禁用loginForm
                .formLogin().disable()
                .logout().disable()
                //禁止frame调用
                .headers().frameOptions().disable()
                .and()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER).and()
                // 除上面外的所有请求全部需要鉴权认证
                .authorizeRequests().anyRequest().authenticated();
    }

}
