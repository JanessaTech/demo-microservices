package com.micro.test;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/test")
public class TestApplication {
    public static void main(String[] args){
        SpringApplication.run(TestApplication.class, args);
    }

    @GetMapping("/msg")
    public String msg(){
        return "hello test";
    }
}
