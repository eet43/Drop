package com.drop.dropshop.deliveryAdmin.controller;

import com.drop.dropshop.deliveryAdmin.dto.DeliveryStatusDto;
import com.drop.dropshop.deliveryAdmin.entity.DeliveryStatus;
import com.drop.dropshop.deliveryAdmin.service.DeliveryStatusAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeliveryStatusController {
    private final DeliveryStatusAdminService deliveryStatusAdminService;

    @Autowired
    public DeliveryStatusController(DeliveryStatusAdminService deliveryStatusAdminService){this.deliveryStatusAdminService = deliveryStatusAdminService;}

    @PostMapping("/api/delivery-status")
    public DeliveryStatus createDeliverySpecification(@RequestBody DeliveryStatusDto requestDto){
        return deliveryStatusAdminService.createDeliveryStatus(requestDto); // request의 데이터가 삽입된 DeliveryStatus 객체 반환
    }
}
