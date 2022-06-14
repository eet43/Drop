package com.drop.dropshop.order.dto;

import com.drop.dropshop.order.entity.Item;
import com.drop.dropshop.order.entity.Order;
import com.drop.dropshop.order.entity.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDto {
    private Long id;
    private Long memberId;
    private Long deliveryId;
    private Item item;
    private OrderStatus orderStatus;
    private LocalDateTime orderDate;

    public OrderDto(Order o) {
        this.id = o.getId();
        this.memberId = o.getMemberId();
        this.deliveryId = o.getDeliveryId();
        this.item = o.getItem();
        this.orderStatus = o.getStatus();
        this.orderDate = o.getOrderDate();
    }

}
