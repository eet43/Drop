package com.drop.dropshop.deliveryAdmin.controller;

import com.drop.dropshop.deliveryAdmin.common.ApiResponse;
import com.drop.dropshop.deliveryAdmin.dto.createDTO.RequestCreateLogDTO;
import com.drop.dropshop.deliveryAdmin.dto.responseDTO.ResponseLocationDTO;
import com.drop.dropshop.deliveryAdmin.service.AnnounceLogService;
import com.drop.dropshop.deliveryAdmin.util.ReverseGeocode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/announce")
@RequiredArgsConstructor
@RestController
public class AnnounceLogController {
    private final AnnounceLogService announceLogService;

    @PostMapping("/log") // 배송요청 단말장치로 부터
    public ApiResponse<String> createAnnounceLog(@RequestBody RequestCreateLogDTO requestCreateLogDTO){
        return ApiResponse.success("result", announceLogService.createAnnounceLog(requestCreateLogDTO));
    }

    @GetMapping("/log/{orderId}") // 사용자의 위치 조회 요청에 응답
    public ApiResponse<ResponseLocationDTO> getLocationlog(@PathVariable String orderId){
        ResponseLocationDTO currentLocation = announceLogService.findAnnounceLog(orderId);
        return ApiResponse.success("result", currentLocation);
    }
}
