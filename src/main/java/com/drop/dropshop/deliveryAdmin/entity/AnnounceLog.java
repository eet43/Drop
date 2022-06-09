package com.drop.dropshop.deliveryAdmin.entity;

import com.drop.dropshop.deliveryAdmin.dto.AnnounceLogDto;
import com.drop.dropshop.deliveryAdmin.dto.DeliveryStatusDto;
import com.drop.dropshop.deliveryAdmin.util.DeliveryLogTimestamped;
import com.drop.dropshop.deliveryAdmin.util.DeliveryStatusTimestamped;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class AnnounceLog extends DeliveryLogTimestamped{
    // 배송 상태 엔티티//
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(example = "db id, apply auto_increase: 배달 상태 로그 테이블 아이디")
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    @ApiModelProperty(example = "orderId, Table identification information: 주문 아이디")
    private String orderId;

    @Column(nullable = false, length = 30)
    @ApiModelProperty(example = "phoneNumber, Identification information for KakaoTalk notification: 카카오톡 알림을 위한 요청자 전화번호")
    private String phoneNumber;

    @Column(nullable = false, length = 150)
    @ApiModelProperty(example = "currentLocation, Current location of drone in delivery: 배송중인 드론의 현재 위치")
    private String currentLocation;


    public AnnounceLog(AnnounceLogDto requestDto){
        this.orderId = requestDto.getOrderId();
        this.phoneNumber = requestDto.getPhoneNumber();
        this.currentLocation = requestDto.getCurrentLocation();
    }
}