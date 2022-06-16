package com.drop.dropshop.deliveryAdmin.entity;

import com.drop.dropshop.deliveryAdmin.dto.createDTO.RequestCreateLogDTO;
import com.drop.dropshop.deliveryAdmin.dto.createDTO.RequestCreateStatusDTO;
import com.drop.dropshop.deliveryAdmin.util.DeliveryStatusTimestamped;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Setter @Getter
@NoArgsConstructor
@Entity
public class DeliveryStatus extends DeliveryStatusTimestamped {
    // 배송 상태 엔티티//
    @Id
    @Column(nullable = false, unique = true, length = 30)
    @ApiModelProperty(example = "orderId, Table identification information: 주문 아이디")
    private String orderId;

    @Column(nullable = false, length = 30)
    @ApiModelProperty(example = "a description of a product: 목적지 주소")
    private String destinationAddress;

    @Column(nullable = false, length = 150)
    @ApiModelProperty(example = "destination_address, a description of a product: 상품 설명")
    private String description;

    @Column(nullable = false)
    @ApiModelProperty(example = "been returned status: 반송여부")
    private boolean returned;

    @Column(nullable = false)
    @ApiModelProperty(example = "delivery success_check, delivery complete or not: 배송 성공 여부")
    private boolean success_check;

    @Builder
    public DeliveryStatus(RequestCreateStatusDTO requestCreateStatusDTO){
        this.orderId = requestCreateStatusDTO.getOrderId();
        this.destinationAddress = requestCreateStatusDTO.getDestinationAddress();
        this.description = requestCreateStatusDTO.getDescription();
        this.returned = false;
        this.success_check = false;
    }
}
