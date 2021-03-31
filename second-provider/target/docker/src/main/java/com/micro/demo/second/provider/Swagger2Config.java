package com.micro.demo.second.provider;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.micro.common.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@EnableKnife4j
public class Swagger2Config {

    @Bean
    public Docket productApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .globalResponseMessage(RequestMethod.GET, globalResponseMessageInInfo())
                .globalResponseMessage(RequestMethod.POST, globalResponseMessageInInfo())
                .globalResponseMessage(RequestMethod.PUT, globalResponseMessageInInfo())
                .globalResponseMessage(RequestMethod.DELETE, globalResponseMessageInInfo())
                .apiInfo(apiInfo())
                .tags(new Tag("second", "second相关操作"), moreTagInfo())
                .select()
                //为当前包下controller生成API文档
                .apis(RequestHandlerSelectors.basePackage("com.micro.demo.second.provider"))
                //only generate Api document for controller with annotation @Api
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                //only generate Api document for methods with @ApiOperation
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(Constants.API_TITLE)
                .description(Constants.project_description)
                .contact(new Contact("Juan","http://juan.com", "lvlajzhao1983@gmail.com"))
                .version(version())
                .build();
    }

    private Tag[] moreTagInfo(){
        List<Tag> tags = new ArrayList<Tag>();
        Tag[] res = new Tag[tags.size()];
        return tags.toArray(res);
    }

    private String version(){
        //String version = contextPath.split("/")[1];
        //return version;
        return "1.0";
    }

    private List<ResponseMessage> globalResponseMessageInInfo(){
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        responseMessageList.add(new ResponseMessageBuilder().code(400).message("逻辑异常").responseModel(new ModelRef("FarmResponse")).build());
        //responseMessageList.add(new ResponseMessageBuilder().code(401).message("越权").responseModel(new ModelRef("FarmResponse")).build());
        //responseMessageList.add(new ResponseMessageBuilder().code(404).message("找不到资源").responseModel(new ModelRef("FarmResponse")).build());
        //responseMessageList.add(new ResponseMessageBuilder().code(409).message("业务逻辑异常").responseModel(new ModelRef("FarmResponse")).build());
        return responseMessageList;
    }

    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeys = new ArrayList<>();
        apiKeys.add(new ApiKey("access_token", "access_token", "query"));
        return apiKeys;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("^(?!auth).*$")).build());
        return securityContexts;
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("access_token", authorizationScopes));
        return securityReferences;
    }
}
