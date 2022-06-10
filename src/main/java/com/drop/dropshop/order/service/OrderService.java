package com.drop.dropshop.order.service;

import com.drop.dropshop.order.entity.Order;

import java.util.List;

public interface OrderService {
    public Long saveOrder(Long itemId, Long memberId, Long deliveryId);

    public void cancelOrder(Long orderId);

    public Order findOrder(Long id);

    public List<Order> findAll();
}
