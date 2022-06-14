package com.drop.dropshop.droneCompany.controller;

import com.drop.dropshop.droneCompany.dto.OwnDroneEnrollDto;
import com.drop.dropshop.droneCompany.dto.OwnDroneResponseDto;
import com.drop.dropshop.droneCompany.exception.NoResourceException;
import com.drop.dropshop.droneCompany.response.ResponseDetails;
import com.drop.dropshop.droneCompany.service.DroneCompanyOwnDroneService;
import com.drop.dropshop.droneCompany.util.HttpStatusChangeInt;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@Slf4j
public class DroneCompanyOwnDroneController {
    private DroneCompanyOwnDroneService droneCompanyOwnDroneService;

    public DroneCompanyOwnDroneController(DroneCompanyOwnDroneService droneCompanyOwnDroneService){
        this.droneCompanyOwnDroneService = droneCompanyOwnDroneService;
    }

    @PostMapping("/api/drone-companies/drones")
    @ApiOperation(value = "드론 업체 보유 드론 등록", notes = "드론 업체가 보유 드론을 등록합니다.")
    public ResponseEntity<?> createOwnDrone(@RequestBody OwnDroneEnrollDto requestDto, HttpServletRequest request) throws NoResourceException {
        OwnDroneResponseDto ownDroneResponseDto = droneCompanyOwnDroneService.enroll(requestDto, request);
        String path = "/api/drone-companies/drones";
        int httpStatus = HttpStatusChangeInt.ChangeStatusCode("CREATED");
        ResponseDetails responseDetails = new ResponseDetails(new Date(), ownDroneResponseDto, httpStatus, path);
        return new ResponseEntity<>(responseDetails, HttpStatus.CREATED);
    }
}
