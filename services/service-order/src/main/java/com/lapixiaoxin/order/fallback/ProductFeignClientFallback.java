package com.lapixiaoxin.order.fallback;

import com.lapixiaoxin.order.feign.ProductFeignClient;
import com.lapixiaoxin.product.bean.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductFeignClientFallback implements ProductFeignClient {
    @Override
    public Product getProductById(Long id) {
        System.out.println("兜底回调...");

        Product product = new Product();
        product.setId(500L);
        product.setPrice(new BigDecimal(200));
        product.setProductName("未知商品");
        product.setNum(20);
        return product;
    }
}
