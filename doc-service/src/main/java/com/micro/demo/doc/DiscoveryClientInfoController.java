package com.micro.demo.doc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("services")
public class DiscoveryClientInfoController {
    private static final String DEFAULT_SWAGGER_URL = "/v2/api-docs";
    private static final String KEY_SWAGGER_URL = "swagger_url";

    private Logger logger = LoggerFactory.getLogger(DiscoveryClientInfoController.class);
    @Autowired
    private DiscoveryClient discoveryClient;

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @GetMapping("/info")
    public void getInfo(){
        discoveryClient.getServices().stream().forEach(serviceId -> {
            logger.info("fetch info for service:" + serviceId);
            List<ServiceInstance>  serviceInstances = discoveryClient.getInstances(serviceId);
            if(serviceInstances == null || serviceInstances.isEmpty()){
                logger.info("No instance for service:" + serviceId);
            }else{
                ServiceInstance instance = serviceInstances.get(0);
                String swaggerURL = getSwaggerURL(instance);
                Optional < Object > jsonData = getSwaggerDefinitionForAPI(serviceId, swaggerURL);

                if (jsonData.isPresent()) {
                    String content = getJSON(serviceId, jsonData.get());
                    //definitionContext.addServiceDefinition(serviceId, content);
                } else {
                    logger.error("Skipping service id : {} Error : Could not get Swagegr definition from API ", serviceId);
                }


            }
        });
    }

    private String getSwaggerURL(ServiceInstance instance) {
        String swaggerURL = instance.getMetadata().get(KEY_SWAGGER_URL);
        return swaggerURL != null ? instance.getUri() + swaggerURL : instance.getUri() + DEFAULT_SWAGGER_URL;
    }

    private Optional < Object > getSwaggerDefinitionForAPI(String serviceName, String url) {
        logger.debug("Accessing the SwaggerDefinition JSON for Service : {} : URL : {} ", serviceName, url);
        try {
            Object jsonData = restTemplate().getForObject(url, Object.class);
            return Optional.of(jsonData);
        } catch (RestClientException ex) {
            logger.error("Error while getting service definition for service : {} Error : {} ", serviceName, ex.getMessage());
            return Optional.empty();
        }
    }

    public String getJSON(String serviceId, Object jsonData) {
        try {
            return new ObjectMapper().writeValueAsString(jsonData);
        } catch (JsonProcessingException e) {
            logger.error("Error : {} ", e.getMessage());
            return "";
        }
    }
}
