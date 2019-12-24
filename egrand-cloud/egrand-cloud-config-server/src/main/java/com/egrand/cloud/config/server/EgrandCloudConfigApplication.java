package com.egrand.cloud.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class EgrandCloudConfigApplication {
    public static void main(String[] args) { SpringApplication.run(EgrandCloudConfigApplication.class, args); }
}
