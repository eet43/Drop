package com.drop.dropshop.deliveryAdmin.dto.updateDTO;


import lombok.Getter;

@Getter
public class RequestUpdateStatusDTO {
    private String orderId;
    private boolean returned;
    private boolean success_check;
}
