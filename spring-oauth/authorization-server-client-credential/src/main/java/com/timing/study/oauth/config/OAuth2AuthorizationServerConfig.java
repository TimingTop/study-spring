package com.timing.study.oauth.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;


// 不需要 SecurityConfig 提供的用户验证功能


@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     *  这个 方法一定要的，否则 会 token 会验证 不通过
     *
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("isAuthenticated()");
    }

    // 这个是 配置 app 的，
    // 这个只支持 client_credentials
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("app1").secret("123")
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("read_userinfo", "read_contacts");

        /*
          password :  密码模式
          authorization_code : 授权码模式   ,需要设置 回调 地址
           implicit : 简化模式，authorization_code 的简化，没有 code 换取 access_token 的过程，直接获取，
           client_credentials: 客户端模式，不需要用户授权，所以要把 spring 提供的用户验证功能去掉，
         */
    }
}
