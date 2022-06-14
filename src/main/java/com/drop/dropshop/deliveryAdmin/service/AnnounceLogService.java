package com.drop.dropshop.deliveryAdmin.service;

import com.drop.dropshop.deliveryAdmin.dto.createDTO.RequestCreateLogDTO;
import com.drop.dropshop.deliveryAdmin.entity.AnnounceLog;
import com.drop.dropshop.deliveryAdmin.repository.AnnounceLogRepository;
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
}
