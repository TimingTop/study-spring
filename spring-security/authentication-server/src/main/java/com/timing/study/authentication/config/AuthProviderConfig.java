package com.timing.study.authentication.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configurable
@EnableWebSecurity
public class AuthProviderConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() // 定义那些 url 需要被保护，那些不需要
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")  // 根据下面配置的用户授权 一些页面
                .anyRequest().authenticated()
                .and()
                .formLogin() // 需要用户登陆时候，转到登陆页面
//                .loginPage("/login").permitAll()
                .and()
                .httpBasic()
                .and()
                .logout().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 配置用户 & 权限
        auth.
                inMemoryAuthentication()
                .withUser("admin1")
                         .password("{noop}admin1")
                         .roles("ADMIN", "USER")
                         .and()
                .withUser("user1")
                         .password("{noop}user1")
                         .roles("USER");
    }

//    public KerberosAuthenticationProvider
}
