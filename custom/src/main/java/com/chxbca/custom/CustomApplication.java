package com.chxbca.custom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author chxbca
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class CustomApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomApplication.class, args);
    }
}
