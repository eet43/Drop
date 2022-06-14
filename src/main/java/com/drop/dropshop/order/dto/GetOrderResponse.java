package com.drop.dropshop.order.dto;

import com.drop.dropshop.order.entity.Item;
import com.drop.dropshop.order.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class GetOrderResponse {
    private Long memberId;

    private Long deliveryId;

    private Item item;

    private OrderStatus status;
    private LocalDateTime orderDate;
}
