package com.tim.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by eminxta on 2016/07/21.
 */
@RestController
@SpringBootApplication
@RibbonClient(name="say-hello",configuration = SayHelloConfiguration.class)
public class UserApplication {

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    public String hi(@RequestParam(value = "name",defaultValue = "Artaban") String name){
        String greeting = this.restTemplate.getForObject("http://localhost:8090/greeting",String.class);
        return String.format("%s,%s!",greeting,name);
    }

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }
}
