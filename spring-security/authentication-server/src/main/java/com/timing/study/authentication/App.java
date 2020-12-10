package com.timing.study.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;

@SpringBootApplication
@ConfigurationProperties(value = "application.yml")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}



/*

查看 spring security 的实现，  可以自定义用户的来源

https://www.cnblogs.com/demingblog/p/10874753.html













 */