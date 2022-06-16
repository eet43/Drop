package com.drop.dropshop.deliveryAdmin.dto.createDTO;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestCreateLogDTO {
    /**
         로그 생성에 관한 request에는 주문 정보(객체 식별), 현재 위치 정보가 들어간다.
         + 전화번호는 이후에 문자 전송이나 kakao api를 이용한 메시지 전송과 상담 기록등에서 활용될 수 있어 저장한다.
         + 첫 로그 생성시 반송 여부(returned), 배송 성공 여부(done)는 당연히 false 이므로 포함되지 않는다.
     */

    private String orderId;             // 주문 id
    private String phoneNumber;         // 사용자 식별,
    private String currentLocation;     // 현재 위치 주소 (ron, rat 좌표값)
}
