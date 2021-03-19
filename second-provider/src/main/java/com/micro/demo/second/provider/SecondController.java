package com.micro.demo.second.provider;

import com.micro.common.FarmResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "这是second", tags = "second")
@RestController
@RequestMapping("/second")
public class SecondController {
    private Logger logger = LoggerFactory.getLogger(SecondController.class);

    @ApiOperation(value = "getMsg", notes = "get msg from second provider")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code  = 400, message = "参数校验异常", response = FarmResponse.class)
    })
    @GetMapping(value = "/msg")
    public String getMsg() throws InterruptedException {
        logger.info("===== call in SecondController ====");
        return "hello, second provider!";
    }
}
