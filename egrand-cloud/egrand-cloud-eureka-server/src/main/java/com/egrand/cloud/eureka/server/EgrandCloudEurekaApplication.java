package com.egrand.cloud.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EgrandCloudEurekaApplication
{
    public static void main(String[] args) throws Exception { SpringApplication.run(EgrandCloudEurekaApplication.class, args); }
}
