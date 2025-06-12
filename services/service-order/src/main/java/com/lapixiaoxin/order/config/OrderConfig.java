package com.lapixiaoxin.order.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OrderConfig {

    /**
     * 配置默认重试机制
     * @return
     */
//    @Bean
    Retryer retryer() {
        return new Retryer.Default();
    }

    /**
     * feign 的日志配置
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @LoadBalanced // 注解式负载均衡
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
