package io.gitee.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

/**
 * 基础Security配置
 *
 * @author Cikaros
 * @date 2021/12/20
 */
@Configuration
@ConditionalOnMissingBean(DefaultWebSecurityConfigurerAdapter.class)
public class DefaultWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    private OAuth2ResourceServerProperties.Opaquetoken resourceServer;

    @Autowired
    public void setResourceServer(OAuth2ResourceServerProperties resourceServer) {
        this.resourceServer = resourceServer.getOpaquetoken();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .antMatchers("/error")
                //开放静态资源
                .antMatchers(
                        HttpMethod.GET,
                        "**.html",
                        "**.css",
                        "**.js",
                        "**.json",
                        "/assets/**",
                        "/images/**",
                        "/favicon.*",
                        "/"
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //禁用csrf
        http.csrf().disable();
        //开放跨域
        http.cors();
        //form登录禁用
        http.logout().disable();
        //设置允许OPTIONS请求
        http.authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll()
                //拦截所有请求
                .and().authorizeRequests().anyRequest().authenticated();

    }

    /**
     * 配置资源服务器如何验证token有效性
     * 1. DefaultTokenServices
     * 如果认证服务器和资源服务器同一服务时,则直接采用此默认服务验证即可
     * 2. RemoteTokenServices (当前采用这个)
     * 当认证服务器和资源服务器不是同一服务时, 要使用此服务去远程认证服务器验证
     */
    @Bean
    public RemoteTokenServices tokenServices() {
        // 资源服务器去远程认证服务器验证 token 是否有效
        RemoteTokenServices service = new RemoteTokenServices();
        // 请求认证服务器验证URL，注意：默认这个端点是拒绝访问的，要设置认证后可访问
        service.setCheckTokenEndpointUrl(resourceServer.getIntrospectionUri());
        // 在认证服务器配置的客户端id
        service.setClientId(resourceServer.getClientId());
        // 在认证服务器配置的客户端密码
        service.setClientSecret(resourceServer.getClientSecret());
        return service;
    }
}
