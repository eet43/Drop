package com.drop.dropshop.droneCompany.controller;

import com.drop.dropshop.droneCompany.dto.DroneCompanyDto;
import com.drop.dropshop.droneCompany.entity.DroneCompany;
import com.drop.dropshop.droneCompany.exception.ErrorDetails;
import com.drop.dropshop.droneCompany.response.ResponseDetails;
import com.drop.dropshop.droneCompany.service.DroneCompanyService;
import com.drop.dropshop.droneCompany.util.HttpStatusChangeInt;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class DroneCompanyController {
    private DroneCompanyService droneCompanyService;

    public DroneCompanyController(DroneCompanyService droneCompanyService) {
        this.droneCompanyService = droneCompanyService;
    }

    @PostMapping("/api/drone-companies")
    @ApiOperation(value = "드론 업체 등록", notes = "관리자가 드론 업체를 등록합니다.")
    public ResponseEntity<?> createCourse(@RequestBody DroneCompanyDto requestDto) {
        DroneCompany droneCompany = droneCompanyService.join(requestDto);
        String path = "/api/drone-companies?id=" + droneCompany.getCompanyId();
        int httpStatus = HttpStatusChangeInt.ChangeStatusCode("CREATED");
        ResponseDetails responseDetails = new ResponseDetails(new Date(), droneCompany, httpStatus, path);
        return new ResponseEntity<>(responseDetails, HttpStatus.CREATED);
    }
}
