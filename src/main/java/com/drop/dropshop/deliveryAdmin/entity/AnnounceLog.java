package com.drop.dropshop.deliveryAdmin.entity;

import com.drop.dropshop.deliveryAdmin.dto.updateDTO.RequestUpdateLogDTO;
import com.drop.dropshop.deliveryAdmin.util.DeliveryLogTimestamped;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@Setter @Getter
@NoArgsConstructor
@Entity
public class AnnounceLog extends DeliveryLogTimestamped{
    // 배송 상태 엔티티//
    @Id
    @Column(nullable = false, unique = true, length = 30)
    @ApiModelProperty(example = "orderId, Table identification information: 주문 아이디")
    private String orderId;

    @Column(nullable = false, length = 30)
    @ApiModelProperty(example = "phoneNumber, Identification information for KakaoTalk notification: 카카오톡 알림, 문자 및 상담을 위한 전화번호")
    private String phoneNumber;

    @Column(nullable = false, length = 30)
    @ApiModelProperty(example = "currentLocation, Current location of drone in delivery: 배송중인 드론의 현재 위치(ron, rat)")
    private String currentLocation;

    @Builder
    public AnnounceLog(String orderId, String phoneNumber, String currentLocation){
        this.orderId = orderId;
        this.phoneNumber = phoneNumber;
        this.currentLocation = currentLocation;
    }

    public void updateLog(RequestUpdateLogDTO requestUpdateLogDTO){
        // 좌표 정보 수정 요청시 필요한 DTO
        this.orderId = requestUpdateLogDTO.getOrderId();
        this.currentLocation = requestUpdateLogDTO.getCurrentLocation();
    }
}