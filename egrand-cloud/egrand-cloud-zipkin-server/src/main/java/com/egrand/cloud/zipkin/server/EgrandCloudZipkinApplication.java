package com.egrand.cloud.zipkin.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin2.server.internal.EnableZipkinServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZipkinServer
public class EgrandCloudZipkinApplication {
    public static void main(String[] args) throws Exception { SpringApplication.run(EgrandCloudZipkinApplication.class, args); }
}
