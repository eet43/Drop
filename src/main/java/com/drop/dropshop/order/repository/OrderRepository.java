package com.drop.dropshop.order.repository;

import com.drop.dropshop.order.dto.OrderDto;
import com.drop.dropshop.order.entity.Order;

import java.util.List;

public interface OrderRepository {
    // 주문 등록
    void save(Order order);

    // 전체 주문 조회
    List<Order> findAll();

    // 특정 주문 조회
    Order findById(Long id);
}
