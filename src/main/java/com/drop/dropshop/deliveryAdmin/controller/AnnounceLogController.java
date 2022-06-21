package com.drop.dropshop.deliveryAdmin.controller;

import com.drop.dropshop.deliveryAdmin.common.ApiResponse;
import com.drop.dropshop.deliveryAdmin.dto.createDTO.RequestCreateLogDTO;
import com.drop.dropshop.deliveryAdmin.dto.responseDTO.ResponseLocationDTO;
import com.drop.dropshop.deliveryAdmin.dto.updateDTO.RequestUpdateLogDTO;
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

    @PostMapping("/log")           // 배송요청 단말장치로 부터
    public ApiResponse<String> createAnnounceLog(@RequestBody RequestCreateLogDTO requestCreateLogDTO){
        return ApiResponse.success("result", announceLogService.createAnnounceLog(requestCreateLogDTO)); // 생성 로그의 orderID 리턴
    }

    @GetMapping("/log/{orderId}") // 사용자의 위치 조회 요청에 응답
    public ApiResponse<ResponseLocationDTO> getLocationlog(@PathVariable String orderId){
        ResponseLocationDTO currentLocation = announceLogService.findAnnounceLog(orderId);
        return ApiResponse.success("result", currentLocation);                                           // 주소가 담긴 ResponseLocationDTO객체 JSON형태로 리턴
    }

    @PutMapping("/log")          // 드론 기지국을 지날 때 마다 위치좌표 갱신요청
    public ApiResponse<String> updateLocation(@RequestBody RequestUpdateLogDTO requestUpdateLogDTO){
        return ApiResponse.success("result", announceLogService.modifyLocationLog(requestUpdateLogDTO)); // 수정된 AnnounceLog의 orderId 리턴
    }
}
