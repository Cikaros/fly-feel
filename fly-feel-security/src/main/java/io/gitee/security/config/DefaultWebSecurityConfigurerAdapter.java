package io.gitee.security.config;

import io.gitee.security.handler.DefaultAccessDeniedHandler;
import io.gitee.security.handler.DefaultLoginFailHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 基础Security配置
 *
 * @author Cikaros
 * @date 2021/12/20
 */
@Configuration
@ConditionalOnMissingBean({WebSecurityConfigurerAdapter.class, ResourceServerConfigurerAdapter.class})
public class DefaultWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Value("${server.error.path:${error.path:/error}}")
    protected String errorPath;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .antMatchers(
                        HttpMethod.GET,
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/**/*.json",
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
        //form登录
        http.formLogin()
                .defaultSuccessUrl("/", true)
                .failureHandler(new DefaultLoginFailHandler(errorPath));
        //退出登录清空认证信息
        http.logout().clearAuthentication(true);
        //设置允许OPTIONS请求
        http.authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll()
                //拦截所有请求
                .and().authorizeRequests().anyRequest().authenticated();
        http.exceptionHandling()
                //默认的无权访问处理器
                .accessDeniedHandler(new DefaultAccessDeniedHandler(errorPath));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

}
