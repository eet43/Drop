package com.drop.dropshop.order.service;

import com.drop.dropshop.order.entity.Order;

public interface OrderService {
    public Long order(Long userId, Long itemId);
}
