package com.drop.dropshop.droneCompany.controller;

import com.drop.dropshop.droneCompany.dto.DroneModelDto;
import com.drop.dropshop.droneCompany.dto.OwnDroneResponseDto;
import com.drop.dropshop.droneCompany.entity.CompanyDroneDetail;
import com.drop.dropshop.droneCompany.entity.DroneModel;
import com.drop.dropshop.droneCompany.exception.NoResourceException;
import com.drop.dropshop.droneCompany.response.ResponseDetails;
import com.drop.dropshop.droneCompany.service.DroneDeliveryService;
import com.drop.dropshop.droneCompany.util.HttpStatusChangeInt;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
@Slf4j
public class DroneDeliveryController {
    private DroneDeliveryService droneDeliveryService;

    public DroneDeliveryController(DroneDeliveryService droneDeliveryService) {
        this.droneDeliveryService = droneDeliveryService;
    }

    @PutMapping("/api/drone-companies/drones/{droneId}/status")
    @ApiOperation(value = "드론 운행 상태 수정", notes = "드론 업체가 드론 운행 상태를 변경합니다.")
    public ResponseEntity<?> updateDroneDelivery(@RequestBody Map<String, String> requestObject, @PathVariable UUID droneId, HttpServletRequest request) throws NoResourceException {
        OwnDroneResponseDto ownDroneResponseDto = droneDeliveryService.update(requestObject, droneId, request);
        String path = "/api/drone-companies/drones/" + droneId + "/status";
        int httpStatus = HttpStatusChangeInt.ChangeStatusCode("OK");
        ResponseDetails responseDetails = new ResponseDetails(new Date(), ownDroneResponseDto, httpStatus, path);
        return new ResponseEntity<>(responseDetails, HttpStatus.OK);
    }

    @PostMapping("/api/drone-companies/drones/{droneId}/location")
    @ApiOperation(value = "드론 위치 전달", notes = "드론에서 보내는 위치를 받아 배달 도메인으로 전달합니다.")
    public ResponseEntity<?> locationDroneDelivery(@RequestBody Map<String, String> requestObject, @PathVariable UUID droneId) throws NoResourceException {
        droneDeliveryService.location(requestObject, droneId);
        String path = "/api/drone-companies/drones/" + droneId + "/location";
        int httpStatus = HttpStatusChangeInt.ChangeStatusCode("OK");
        ResponseDetails responseDetails = new ResponseDetails(new Date(), "", httpStatus, path);
        return new ResponseEntity<>(responseDetails, HttpStatus.OK);
    }
}
