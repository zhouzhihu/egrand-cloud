package com.egrand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * 平台基础服务
 * 代码生成器
 * @author ZZH
 */
@EnableDiscoveryClient
@SpringBootApplication
public class EgrandCloudGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(EgrandCloudGeneratorApplication.class, args);
    }
}
