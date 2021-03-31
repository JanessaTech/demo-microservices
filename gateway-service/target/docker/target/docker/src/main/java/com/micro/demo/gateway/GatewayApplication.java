package com.micro.demo.gateway;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.time.Duration;

@SpringBootApplication
@EnableEurekaClient
public class GatewayApplication {
    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer(){
        return factory -> factory.configureDefault(
                id -> new Resilience4JConfigBuilder(id)
                        .timeLimiterConfig(TimeLimiterConfig.custom()
                                .timeoutDuration(Duration.ofSeconds(4)).build())
                        .circuitBreakerConfig(CircuitBreakerConfig.custom()
                                .slidingWindowSize(10)
                                .failureRateThreshold(33.3F)
                                .slowCallRateThreshold(33.3F)
                                .build())
                        .build());
    }

    public static void main(String[] args){
        //test
        // http://127.0.0.1:8771/first-provider/first/call_second
        SpringApplication.run(GatewayApplication.class, args);
    }
}
