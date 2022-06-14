package com.drop.dropshop.droneCompany.entity;

public enum RaceCode {
    END, // 운행 종료
    PROCESSING, // 운행 중
    READYING, // 운행 준비중 (주문을 받고 배송 시작 알림을 받기 전)
    WAITING, // 운행 대기중 -> 운행을 시작할 수 있는 상태
    IMPOSSIBLE, // 운행 불가능
}
