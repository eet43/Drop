package com.drop.dropshop.droneCompany.controller;

import com.drop.dropshop.droneCompany.dto.DroneCompanyDto;
import com.drop.dropshop.droneCompany.dto.DroneModelDto;
import com.drop.dropshop.droneCompany.entity.DroneCompany;
import com.drop.dropshop.droneCompany.entity.DroneModel;
import com.drop.dropshop.droneCompany.exception.BusinessNumberNotValidException;
import com.drop.dropshop.droneCompany.response.ResponseDetails;
import com.drop.dropshop.droneCompany.service.DroneModelService;
import com.drop.dropshop.droneCompany.util.HttpStatusChangeInt;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Slf4j
public class DroneModelController {
    private DroneModelService droneModelService;

    public DroneModelController(DroneModelService droneModelService){
        this.droneModelService = droneModelService;
    }

    @PostMapping("/api/drone-models")
    @ApiOperation(value = "드론 모델 등록", notes = "관리자가 드론 모델을 등록합니다.")
    public ResponseEntity<?> createDroneModel(@RequestBody DroneModelDto requestDto) {
        DroneModel droneModel = droneModelService.enroll(requestDto);
        String path = "/api/drone-models/" + droneModel.getModelId();
        int httpStatus = HttpStatusChangeInt.ChangeStatusCode("CREATED");
        ResponseDetails responseDetails = new ResponseDetails(new Date(), droneModel, httpStatus, path);
        return new ResponseEntity<>(responseDetails, HttpStatus.CREATED);
    }
}
