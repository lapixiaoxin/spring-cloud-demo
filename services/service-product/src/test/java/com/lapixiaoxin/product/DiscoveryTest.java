package com.lapixiaoxin.product;

import com.alibaba.cloud.nacos.discovery.NacosServiceDiscovery;
import com.alibaba.nacos.api.exception.NacosException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

@SpringBootTest
public class DiscoveryTest {

    // SpringCloud框架统一的服务发现类
    @Autowired
    private DiscoveryClient discoveryClient;

    // Nacos组建自身提供的服务发现类
    @Autowired
    private NacosServiceDiscovery nacosServiceDiscovery;

    @Test
    public void testNacosServiceDiscovery() throws NacosException {
        for (String service : nacosServiceDiscovery.getServices()) {
            System.out.println(service);
            List<ServiceInstance> instances = nacosServiceDiscovery.getInstances(service);
            for (ServiceInstance instance : instances) {
                System.out.println(instance.getHost() + ":" + instance.getPort());
            }
        }
    }


    @Test
    public void testDiscoveryClient() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            System.out.println(service);
            // 获取ip和port
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            for (ServiceInstance instance : instances) {
                System.out.println(instance.getHost() + ":" + instance.getPort());
            }
        }
    }

}
