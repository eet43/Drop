package com.drop.dropshop.deliveryAdmin.service;

import com.drop.dropshop.deliveryAdmin.dto.DeliveryStatusDto;
import com.drop.dropshop.deliveryAdmin.entity.DeliveryStatus;
import com.drop.dropshop.deliveryAdmin.repository.DeliveryStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class DeliveryStatusAdminService {
    private final DeliveryStatusRepository deliveryStatusRepository;

    @Autowired
    public DeliveryStatusAdminService(DeliveryStatusRepository deliveryStatusRepository){ this.deliveryStatusRepository = deliveryStatusRepository;}

    @Transactional
    public DeliveryStatus createDeliveryStatus(DeliveryStatusDto requestDto){
        DeliveryStatus deliveryStatus = new DeliveryStatus(requestDto);
        this.deliveryStatusRepository.save(deliveryStatus);
        return deliveryStatus;
    }
}
