package com.drop.dropshop.order.service;

import com.drop.dropshop.order.entity.Order;

public interface OrderService {
    public Long saveOrder(Long itemId, Long memberId, Long deliveryId);

    public void cancelOrder(Long orderId);
}
