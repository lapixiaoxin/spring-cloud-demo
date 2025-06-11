package com.lapixiaoxin.order.service;

import com.lapixiaoxin.order.bean.Order;

public interface OrderService {

    Order createOrder(Long userId, Long productId);
}
