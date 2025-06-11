package com.lapixiaoxin.order.bean;

import com.lapixiaoxin.product.bean.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Order {
    private Long orderId;
    private BigDecimal totalAmount;
    private Long userId;
    private String nickName;
    private String address;
    private List<Product> productList;
}
