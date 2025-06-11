package com.lapixiaoxin.order.controller;

import com.lapixiaoxin.order.bean.Order;
import com.lapixiaoxin.order.config.OrderProperties;
import com.lapixiaoxin.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RefreshScope
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderProperties orderProperties;

//    @Value("${order.timeout}")
//    private String timeout;
//    @Value("${order.auto-confirm}")
//    private String autoConfirm;

    @GetMapping("/config")
    public String config() {
        return "order.timeout=" + orderProperties.getTimeout() + " order.autoConfirm=" + orderProperties.getAutoConfirm() + " order.dbUrl=" + orderProperties.getDbUrl();
    }


    @GetMapping("/order/create")
    public Order creteOrder(@RequestParam("userId") Long userId, @RequestParam("productId") Long productId) {

        Order order = orderService.createOrder(userId, productId);
        return order;
    }

}
