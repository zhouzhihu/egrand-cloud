package com.egrand;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@MapperScan(basePackages = "com.egrand.cloud.ram.server.mapper")
public class EgrandCloudRamApplication {
    public static void main(String[] args) {
        SpringApplication.run(EgrandCloudRamApplication.class, args);
    }
}