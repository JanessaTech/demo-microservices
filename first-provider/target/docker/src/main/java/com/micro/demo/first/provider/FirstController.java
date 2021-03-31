package com.micro.demo.first.provider;

import com.micro.common.FarmResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Api(value = "这是first", tags = "first")
@RestController
@RefreshScope
@RequestMapping("/first")
public class FirstController {
    private Logger logger = LoggerFactory.getLogger(FirstController.class);
    @Value("${welcome.msg}")
    private String welcomeInfo;

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation(value = "getMsg", notes = "get msg from first provider")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code  = 400, message = "参数校验异常", response = FarmResponse.class)
    })
    @GetMapping(value = "/msg")
    public String getMsg() throws InterruptedException {
        logger.info("===== call in FirstController ====");
        return welcomeInfo;
    }

    // test http://127.0.0.1:8765/first-provider/first/call_second
    @ApiOperation(value = "getMsgFromSecond", notes = "get msg from second provider in first provider")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code  = 400, message = "参数校验异常", response = FarmResponse.class)
    })
    @GetMapping(value = "/call_second")
    public String callSecond(){
        logger.info("==== call in callSecond method of FirstController");
        return restTemplate.getForObject("http://second-provider/second-provider/second/msg", String.class);

    }
}
