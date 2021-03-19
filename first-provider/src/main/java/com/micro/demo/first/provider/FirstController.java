package com.micro.demo.first.provider;

import com.micro.common.FarmResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "这是first", tags = "first")
@Validated
@RestController
@RefreshScope
@RequestMapping("/first")
public class FirstController {
    private Logger logger = LoggerFactory.getLogger(FirstController.class);
    @Value("${welcome.msg}")
    private String welcomeInfo;

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
}
