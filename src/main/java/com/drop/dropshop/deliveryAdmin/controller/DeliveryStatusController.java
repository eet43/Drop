package com.drop.dropshop.deliveryAdmin.controller;

import com.drop.dropshop.deliveryAdmin.common.ApiResponse;
import com.drop.dropshop.deliveryAdmin.dto.createDTO.RequestCreateStatusDTO;
import com.drop.dropshop.deliveryAdmin.entity.DeliveryStatus;
import com.drop.dropshop.deliveryAdmin.service.DeliveryStatusAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeliveryStatusController {
    private final DeliveryStatusAdminService deliveryStatusAdminService;

    @PostMapping("/api/delivery-status")
    public ApiResponse<String> createDeliverySpecification(@RequestBody RequestCreateStatusDTO requestCreateStatusDTO){
        return ApiResponse.success("result", deliveryStatusAdminService.createDeliveryStatus(requestCreateStatusDTO)); // request의 데이터가 삽입된 DeliveryStatus 객체 반환
    }
}
