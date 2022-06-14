package com.drop.dropshop.order.dto;

import com.drop.dropshop.order.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CancelOrderResponse {
    private Long id;
    private OrderStatus status;
}
