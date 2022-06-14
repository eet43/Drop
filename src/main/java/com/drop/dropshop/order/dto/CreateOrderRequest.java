package com.drop.dropshop.order.dto;

import lombok.Data;

@Data
public class CreateOrderRequest {
    private Long itemId;
    private Long memberId;
    private Long deliveryId;


}
