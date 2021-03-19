package com.micro.demo.doc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DocApplication {
    public static void main(String[] args){

        SpringApplication.run(DocApplication.class, args);
    }
}
