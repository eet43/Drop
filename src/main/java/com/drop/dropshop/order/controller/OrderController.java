package com.drop.dropshop.order.controller;

import com.drop.dropshop.order.dto.*;
import com.drop.dropshop.order.entity.Order;
import com.drop.dropshop.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 2022-06-14
 * 주문 컨트롤러
 * 1. 전체 주문 조회
 * 2. 특정 주문 조회 (ID)
 * 3. 주문 등록 (ID)
 * 4. 주문 취소 (ID)
 */

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/api/orders")
    public List<OrderDto> findOrders() {
        List<Order> orders = orderService.findAll();

        List<OrderDto> result = orders.stream()
                .map(o->new OrderDto(o))
                .collect(Collectors.toList());

        return result;
    }

    @GetMapping("/api/orders/{id}")
    public GetOrderResponse findOrder(@PathVariable("id") Long id) {
        Order findOrder = orderService.findOrder(id);

        return new GetOrderResponse(findOrder.getMemberId(), findOrder.getDeliveryId(), findOrder.getItem(), findOrder.getStatus(), findOrder.getOrderDate());
    }

    @PostMapping("/api/orders")
    public CreateOrderResponse postOrder(@RequestBody @Valid CreateOrderRequest request) {
        Long orderId = orderService.saveOrder(request.getItemId(), request.getMemberId(), request.getDeliveryId());

        return new CreateOrderResponse(orderId);
    }

    @PutMapping("/api/orders/{id}")
    public CancelOrderResponse cancelOrder(@PathVariable("id") Long id) {
        Order findOrder = orderService.findOrder(id);
        findOrder.cancelOrder();
        return new CancelOrderResponse(findOrder.getId(), findOrder.getStatus());
    }
}
