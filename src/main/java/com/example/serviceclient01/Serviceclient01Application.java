package com.example.serviceclient01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
//开启熔断
@EnableCircuitBreaker
public class Serviceclient01Application {

    public static void main(String[] args) {
        SpringApplication.run(Serviceclient01Application.class, args);
    }

}
