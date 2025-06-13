package com.lapixiaoxin.product.controller;

import com.lapixiaoxin.product.bean.Product;
import com.lapixiaoxin.product.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
//@RequestMapping("/api/product")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 查询商品
     */
    @GetMapping("product/{id}")
    public Product getProduct(@PathVariable("id") Long id, HttpServletRequest request) {
        String header = request.getHeader("X-Token");
        System.out.println("header: " + header);
        log.info("hello");
        Product product = productService.getProductById(id);
        return product;
    }

}
