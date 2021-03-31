package com.micro.demo.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {
    @GetMapping("/inCaseOfFailureUseThis")
    public String inCaseOfFailureUseThis() {
        String response = "服务暂时不可用";
        return response;
    }
}
