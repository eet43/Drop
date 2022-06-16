package com.drop.dropshop.deliveryAdmin.dto.createDTO;


import lombok.Getter;

@Getter
public class RequestCreateStatusDTO {
    /*
        배송 상태에 관한 정보 생성 요청을 위한 DTO.
        + DeliveryStatus 엔티티에 관한 DTO
        + DeliveryStatus의 생성자에서 Getter 참조
     */
    private String orderId;             // 주문 식별 ID
    private String destinationAddress;  // 목적지 주소
    private String description;         // 상품 설명
}
