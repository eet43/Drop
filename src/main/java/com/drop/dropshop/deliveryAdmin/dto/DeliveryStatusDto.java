package com.drop.dropshop.deliveryAdmin.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DeliveryStatusDto {
    private String orderId;                 // 주문 id
    private String destination_address;     // 목적지 주소
    private String description;             // 상품 설명
    private boolean returned;               // 반송 여부
    private boolean success_check;          // 배송 완료 여부 (배송, 반송 둘 다 해당)
}
