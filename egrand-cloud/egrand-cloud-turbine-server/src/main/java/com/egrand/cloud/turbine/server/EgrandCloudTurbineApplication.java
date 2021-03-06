package com.egrand.cloud.turbine.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableTurbine
@EnableHystrixDashboard
public class EgrandCloudTurbineApplication
{
    public static void main(String[] args) throws Exception { SpringApplication.run(EgrandCloudTurbineApplication.class, args); }
}
