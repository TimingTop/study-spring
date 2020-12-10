package com.timing.study.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenStore tokenStore;

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("my-sign-key");
        return converter;
    }
    @Bean
    public JwtTokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                // 可以配置 存进 mysql 或者 redis
//                .tokenStore(XXX)
        // accessToken 的格式，可以是 jwt格式，使用 access token 的同时，还存了其他信息
        // 一举两得， 并且可以 自验证，不需要把 access token 通过 http 给 授权服务器验证
        .accessTokenConverter(jwtAccessTokenConverter());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("isAuthenticated()");
    }

    // 这个是 配置 app 的，
    // 这个配置 支持  password  authorization_code  implicit
    //
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("app1").secret("123")
//                .authorizedGrantTypes("password", "authorization_code")
                .authorizedGrantTypes("password", "implicit", "refresh_token")     // implicit 跟 authorization_code 是同一个过程的，两者是互斥的，
                .redirectUris("http://127.0.0.1:8085/callback")
                .scopes("read_userinfo", "read_contacts")
        ;

        /*
          password :  密码模式
          authorization_code : 授权码模式   ,需要设置 回调 地址
           implicit : 简化模式，authorization_code 的简化，没有 code 换取 access_token 的过程，直接获取，

           refresh_token: 刷新 token
         */
    }
}
