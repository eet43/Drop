package com.drop.dropshop.deliveryAdmin.service;

import com.drop.dropshop.deliveryAdmin.dto.createDTO.RequestCreateLogDTO;
import com.drop.dropshop.deliveryAdmin.dto.responseDTO.ResponseLocationDTO;
import com.drop.dropshop.deliveryAdmin.entity.AnnounceLog;
import com.drop.dropshop.deliveryAdmin.repository.AnnounceLogRepository;
import com.drop.dropshop.deliveryAdmin.util.ReverseGeocode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AnnounceLogService {
    private final AnnounceLogRepository announceLogRepository;

    public String createAnnounceLog(RequestCreateLogDTO requestCreateLogDTO){
        AnnounceLog announceLog = AnnounceLog.builder()
                .orderId(requestCreateLogDTO.getOrderId())
                .phoneNumber(requestCreateLogDTO.getPhoneNumber())
                .currentLocation(requestCreateLogDTO.getCurrentLocation())
                .build();
        return this.announceLogRepository.save(announceLog).getOrderId();
    }

    public ResponseLocationDTO findAnnounceLog(String orderId){
        ReverseGeocode reverseGeocode = new ReverseGeocode(); // DB에 있는 좌표값(currentLocation)을 주소로 변환하기 위해 ReverseGeocode 객체 생성
        AnnounceLog announceLog = announceLogRepository.findById(orderId).orElseThrow(
                ()-> new NullPointerException("아이디가 존재하지 않습니다람쥐")
        );
        String currentLog = announceLog.getCurrentLocation(); 
        return reverseGeocode.ReverseGeo(currentLog); // 가장 최근 드론 위치의 위치값이 파싱된 ResponseLocationDTO 리턴 
    }
}
