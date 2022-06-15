package com.drop.dropshop.deliveryAdmin.dto.updateDTO;

import lombok.Builder;
import lombok.Getter;


@Getter
public class RequestUpdateLogDTO {
    private String orderId;
    private String currentLocation;

    RequestUpdateLogDTO(String orderId, String currentLocation){
        this.orderId = orderId;
        this.currentLocation = currentLocation;
    }
}
