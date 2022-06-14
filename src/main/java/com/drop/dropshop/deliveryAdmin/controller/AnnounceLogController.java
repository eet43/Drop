package com.drop.dropshop.deliveryAdmin.controller;

import com.drop.dropshop.deliveryAdmin.common.ApiResponse;
import com.drop.dropshop.deliveryAdmin.dto.createDTO.RequestCreateLogDTO;
import com.drop.dropshop.deliveryAdmin.service.AnnounceLogService;
import com.drop.dropshop.deliveryAdmin.util.ReverseGeocode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/announce")
@RequiredArgsConstructor
@RestController
public class AnnounceLogController {
    private final AnnounceLogService announceLogService;

    @PostMapping("/log") // 배송요청 단말장치로 부터
    public ApiResponse<String> createAnnounceLog(@RequestBody RequestCreateLogDTO requestCreateLogDTO){
        return ApiResponse.success("result", announceLogService.createAnnounceLog(requestCreateLogDTO));
    }
}
