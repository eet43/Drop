package com.drop.dropshop.order.service;

import com.drop.dropshop.order.entity.Item;
import com.drop.dropshop.order.entity.Order;
import com.drop.dropshop.order.repository.ItemRepository;
import com.drop.dropshop.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService{

    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;


    @Override
    @Transactional
    public Long saveOrder(Long itemId, Long memberId, Long deliveryId) {
        // 엔티티 조회
        Item findItem = itemRepository.findById(itemId);

        Order order = Order.createOrder(findItem, memberId, deliveryId);
        orderRepository.save(order);

        return order.getId();
    }
    @Transactional
    @Override
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId);
        order.cancelOrder();
    }

    @Override
    public Order findOrder(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
