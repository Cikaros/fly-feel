package io.gitee.oauth.config;

import io.gitee.define.service.impl.DefaultClientDetailsService;
import io.gitee.define.service.impl.DefaultUserDetailsService;
import io.gitee.oauth.filter.DefaultClientCredentialsTokenEndpointFilter;
import io.gitee.oauth.handler.DefaultWebResponseExceptionTranslator;
import io.gitee.security.handler.AuthExceptionEntryPoint;
import io.gitee.security.handler.DefaultAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * OAuth2服务配置
 *
 * @author Cikaros
 * @date 2021/7/27
 */
@Configuration
@EnableAuthorizationServer
@ConditionalOnMissingBean(DefaultAuthorizationServerConfigurerAdapter.class)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class DefaultAuthorizationServerConfigurerAdapter extends AuthorizationServerConfigurerAdapter {

    @Value("${server.error.path:${error.path:/error}}")
    protected String errorPath;

    private AuthenticationManager authenticationManager;

    private UserDetailsService userDetailsService;

    private ClientDetailsService clientDetailsService;

    private AuthorizationServerTokenServices tokenServices;

    private TokenStore tokenStore;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setClientDetailsService(DefaultClientDetailsService clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }

    @Autowired
    public void setUserDetailsService(DefaultUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setTokenServices(DefaultTokenServices tokenServices) {
        this.tokenServices = tokenServices;
    }

    @Autowired
    public void setTokenStore(JdbcTokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    /**
     * authorizedGrantTypes:
     * 1.授权码模式（authorization code）
     * 2.简化模式（implicit）
     * 3.密码模式（resource owner password credentials）
     * 4.客户端模式（client credentials）
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .withClientDetails(clientDetailsService);
    }

    /**
     * 认证服务端点配置
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                //用户管理
                .userDetailsService(userDetailsService)
                //token生成
                .tokenServices(tokenServices)
                //token存储器
                .tokenStore(tokenStore)
                //拦截器
                //.addInterceptor(requestPrintInterceptor)
                //启用oauth2管理
                .authenticationManager(authenticationManager)//密码模式
                //异常处理
                .exceptionTranslator(new DefaultWebResponseExceptionTranslator())
                //接收GET和POST
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }

    /**
     * 用来配置令牌端点(Token Endpoint)的安全约束
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        //认证过滤器
        DefaultClientCredentialsTokenEndpointFilter filter = new DefaultClientCredentialsTokenEndpointFilter(oauthServer);
        filter.setAuthenticationEntryPoint(new AuthExceptionEntryPoint(errorPath));
        filter.afterPropertiesSet();
        // 客户端认证之前的过滤器
        oauthServer
                //允许表单认证
                .allowFormAuthenticationForClients()
                //加密方式
                .passwordEncoder(passwordEncoder)
                // /oauth/token_key公开
                .tokenKeyAccess("permitAll()")
                // /oauth/check_token公开
                .checkTokenAccess("isAuthenticated()")
                .authenticationEntryPoint(new AuthExceptionEntryPoint(errorPath))
                .accessDeniedHandler(new DefaultAccessDeniedHandler(errorPath))
                //统一认证前的失败信息格式
                .addTokenEndpointAuthenticationFilter(filter);


    }

    @Bean
    @ConditionalOnMissingBean(DefaultTokenServices.class)
    public DefaultTokenServices defaultTokenServices(DefaultClientDetailsService clientDetailsService, JdbcTokenStore tokenStore, AuthenticationManager authenticationManager) throws Exception {
        DefaultTokenServices services = new DefaultTokenServices();
        //配置token存储
        services.setClientDetailsService(clientDetailsService);
        //配置authenticationManager
        services.setAuthenticationManager(authenticationManager);
        services.setTokenStore(tokenStore);
        //开启支持refresh_token，此处如果之前没有配置，启动服务后再配置重启服务，可能会导致不返回token的问题，解决方式：清除redis对应token存储
        services.setSupportRefreshToken(true);
        // 允许重复使用refresh token
        services.setReuseRefreshToken(false);
        services.afterPropertiesSet();
        return services;
    }


    @Bean
    @ConditionalOnMissingBean(TokenStore.class)
    public JdbcTokenStore jdbcTokenStore(DataSource dataSources) {
        return new JdbcTokenStore(dataSources);
    }


}
