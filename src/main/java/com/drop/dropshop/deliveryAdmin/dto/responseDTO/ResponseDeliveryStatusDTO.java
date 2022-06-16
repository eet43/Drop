package com.drop.dropshop.deliveryAdmin.dto.responseDTO;


import com.drop.dropshop.deliveryAdmin.entity.DeliveryStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseDeliveryStatusDTO {
    private String orderID;
    private String destinationAddress;
    private String description;
    private boolean returned;
    private boolean success_check;

    @Builder
    public ResponseDeliveryStatusDTO(DeliveryStatus deliveryStatus){
        this.orderID = deliveryStatus.getOrderId();
        this.destinationAddress = deliveryStatus.getDestinationAddress();
        this.description = deliveryStatus.getDescription();
        this.returned = deliveryStatus.isReturned();
        this.success_check = deliveryStatus.isSuccess_check();
    }
}
