package com.drop.dropshop.deliveryAdmin.controller;

import com.drop.dropshop.deliveryAdmin.common.ApiResponse;
import com.drop.dropshop.deliveryAdmin.dto.createDTO.RequestCreateStatusDTO;
import com.drop.dropshop.deliveryAdmin.dto.responseDTO.ResponseDeliveryStatusDTO;
import com.drop.dropshop.deliveryAdmin.service.DeliveryStatusAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/delivery-status")
public class DeliveryStatusController {
    private final DeliveryStatusAdminService deliveryStatusAdminService;

    @PostMapping("/specification")
    public ApiResponse<String> createDeliverySpecification(@RequestBody RequestCreateStatusDTO requestCreateStatusDTO){
        return ApiResponse.success("result", deliveryStatusAdminService.createDeliveryStatus(requestCreateStatusDTO)); // request의 데이터가 삽입된 DeliveryStatus 객체 반환
    }

    @GetMapping("/specification/{orderId}")
    public ApiResponse<ResponseDeliveryStatusDTO> getDeliverySpecification(@PathVariable String orderId){
        return ApiResponse.success("result", deliveryStatusAdminService.getDeliveryStatus(orderId));
    }
}
