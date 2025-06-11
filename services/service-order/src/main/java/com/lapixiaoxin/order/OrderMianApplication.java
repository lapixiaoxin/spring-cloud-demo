package com.lapixiaoxin.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class OrderMianApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderMianApplication.class, args);
    }
}
