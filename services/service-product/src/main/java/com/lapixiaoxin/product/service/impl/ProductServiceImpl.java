package com.lapixiaoxin.product.service.impl;

import com.lapixiaoxin.product.bean.Product;
import com.lapixiaoxin.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public Product getProductById(Long productId) {
        Product product = new Product();
        product.setId(productId);
        product.setPrice(BigDecimal.valueOf(100));
        product.setProductName("apple");
        product.setNum(2);

   /*     try {
            TimeUnit.SECONDS.sleep(70);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/


        return product;
    }
}
