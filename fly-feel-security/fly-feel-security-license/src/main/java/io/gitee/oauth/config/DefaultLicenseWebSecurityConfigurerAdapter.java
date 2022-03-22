package io.gitee.oauth.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

/**
 * 基础Security配置
 *
 * @author Cikaros
 * @date 2021/12/20
 */
@Primary
@Configuration
@ConditionalOnMissingBean(DefaultLicenseWebSecurityConfigurerAdapter.class)
public class DefaultLicenseWebSecurityConfigurerAdapter extends DefaultResourceWebSecurityConfigurerAdapter {

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

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
