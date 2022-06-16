package com.drop.dropshop.deliveryAdmin.service;

import com.drop.dropshop.deliveryAdmin.dto.createDTO.RequestCreateStatusDTO;
import com.drop.dropshop.deliveryAdmin.dto.responseDTO.ResponseDeliveryStatusDTO;
import com.drop.dropshop.deliveryAdmin.entity.DeliveryStatus;
import com.drop.dropshop.deliveryAdmin.repository.DeliveryStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DeliveryStatusAdminService {
    private final DeliveryStatusRepository deliveryStatusRepository;

    public String createDeliveryStatus(RequestCreateStatusDTO requestCreateStatusDTO){
        DeliveryStatus deliveryStatus = DeliveryStatus.builder()
                .requestCreateStatusDTO(requestCreateStatusDTO)
                .build();
        return deliveryStatusRepository.save(deliveryStatus).getOrderId();
    }

    public ResponseDeliveryStatusDTO getDeliveryStatus(String orderId){
        DeliveryStatus deliveryStatus = deliveryStatusRepository.findById(orderId).orElseThrow(
                ()-> new NullPointerException("아이디가 존재하지 않습니다람쥐")
        );
        return ResponseDeliveryStatusDTO.builder()
                .deliveryStatus(deliveryStatus)
                .build();
    }
}
