package com.lapixiaoxin.order.feign;

import com.lapixiaoxin.order.fallback.ProductFeignClientFallback;
import com.lapixiaoxin.product.bean.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// 如果没有配置contextId指定请求段Id，则默认使用value代替
@FeignClient(value = "service-product", contextId = "service-product", fallback = ProductFeignClientFallback.class)
public interface ProductFeignClient {

    //mvc注解的两套使用逻辑
    //1、标注在Controller上，是接受这样的请求
    //2、标注在FeignClient上，是发送这样的请求
    @GetMapping("/product/{id}")
    Product getProductById(@PathVariable("id") Long id);

}
