package com.lapixiaoxin.order.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.lapixiaoxin.order.bean.Order;
import com.lapixiaoxin.order.feign.ProductFeignClient;
import com.lapixiaoxin.order.service.OrderService;
import com.lapixiaoxin.product.bean.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired //一定导入 spring-cloud-starter-loadbalancer
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private ProductFeignClient productFeignClient;

    @SentinelResource(value = "createOrder")
    @Override
    public Order createOrder(Long userId, Long productId) {
//        Product product = getProductFromRemoteWithLoadBalanceAnnotation(productId);
        // 使用OpenFeign进行远程调用
        Product product = productFeignClient.getProductById(productId);
        Order order = new Order();
        order.setOrderId(123L);
        order.setTotalAmount(product.getPrice().multiply(new BigDecimal(product.getNum())));
        order.setUserId(userId);
        order.setNickName("lapixiaoxin");
        order.setAddress("none");
        order.setProductList(Arrays.asList(product));
        return order;
    }

    // 进阶3：基于注解的负载均衡
    private Product getProductFromRemoteWithLoadBalanceAnnotation(Long productId) {
        String url = "http://service-product/product/" + productId;
        //2、给远程发送请求； service-product 会被动态替换
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }


    // 进阶2：完成负载均衡发送请求
    private Product getProductFromRemoteWithLoadBalance(Long productId) {
        //1、获取到商品服务所在的所有机器IP+port
        ServiceInstance choose = loadBalancerClient.choose("service-product");
        //远程URL
        String url = "http://" + choose.getHost() + ":" + choose.getPort() + "/product/" + productId;
        log.info("远程请求：{}", url);
        //2、给远程发送请求
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }


    private Product getProductFromRemote(Long productId) {
        //1、获取到商品服务所在的所有机器IP+port
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");

        ServiceInstance instance = instances.get(0);
        //远程URL
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/product/" + productId;
        log.info("远程请求：{}", url);
        //2、给远程发送请求
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }
}
