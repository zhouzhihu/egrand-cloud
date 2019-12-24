package com.egrand;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@EnableFeignClients
@MapperScan(basePackages = "com.egrand.cloud.yuncang.file.server.mapper")
@SpringBootApplication
public class YunCangFileApplication {
    public static void main(String[] args) {
        SpringApplication.run(YunCangFileApplication.class, args);
    }
}