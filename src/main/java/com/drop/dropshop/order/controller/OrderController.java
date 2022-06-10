package com.drop.dropshop.order.controller;

import com.drop.dropshop.order.dto.CreateOrderRequest;
import com.drop.dropshop.order.dto.OrderDto;
import com.drop.dropshop.order.dto.CreateOrderResponse;
import com.drop.dropshop.order.entity.Order;
import com.drop.dropshop.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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

    @PostMapping("/api/orders")
    public CreateOrderResponse postOrder(@RequestBody @Valid OrderDto) {

    }
}
