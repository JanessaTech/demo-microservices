package com.micro.demo.second.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SecondApplication {
    public static void main(String[] args){
        SpringApplication.run(SecondApplication.class, args);
    }
}
