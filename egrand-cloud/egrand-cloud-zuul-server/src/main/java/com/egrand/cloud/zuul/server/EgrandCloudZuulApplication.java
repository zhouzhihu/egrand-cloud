package com.egrand.cloud.zuul.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@EnableFeignClients
@EnableHystrix
public class EgrandCloudZuulApplication
{
    public static void main(String[] args) throws Exception { SpringApplication.run(EgrandCloudZuulApplication.class, args); }

//    @Bean
//    @RefreshScope
//    @ConfigurationProperties("zuul")
//    public ZuulProperties zuulProperties() { return new ZuulProperties(); }
}
