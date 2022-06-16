package com.drop.dropshop.deliveryAdmin.service;

import com.drop.dropshop.deliveryAdmin.dto.createDTO.RequestCreateStatusDTO;
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
}
