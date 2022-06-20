package com.drop.dropshop.droneCompany.repository.controller;

import com.drop.dropshop.droneCompany.dto.DroneDetailDto;
import com.drop.dropshop.droneCompany.dto.OwnDroneEnrollDto;
import com.drop.dropshop.droneCompany.dto.OwnDroneResponseDto;
import com.drop.dropshop.droneCompany.dto.UUIDDto;
import com.drop.dropshop.droneCompany.exception.NoResourceException;
import com.drop.dropshop.droneCompany.response.ResponseDetails;
import com.drop.dropshop.droneCompany.service.DroneCompanyOwnDroneService;
import com.drop.dropshop.droneCompany.util.HttpStatusChangeInt;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
public class DroneCompanyOwnDroneController {
    private DroneCompanyOwnDroneService droneCompanyOwnDroneService;

    public DroneCompanyOwnDroneController(DroneCompanyOwnDroneService droneCompanyOwnDroneService) {
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

    @PutMapping("/api/drone-companies/drones/{companyDroneId}")
    @ApiOperation(value = "드론 업체 보유 드론 수정", notes = "드론 업체가 보유 드론을 수정합니다.")
    public ResponseEntity<?> updateOwnDrone(@RequestBody OwnDroneEnrollDto requestDto, HttpServletRequest request, @PathVariable UUID companyDroneId) throws NoResourceException {
        OwnDroneResponseDto ownDroneResponseDto = droneCompanyOwnDroneService.update(requestDto, request, companyDroneId);
        String path = "/api/drone-companies/drones/" + companyDroneId;
        int httpStatus = HttpStatusChangeInt.ChangeStatusCode("OK");
        ResponseDetails responseDetails = new ResponseDetails(new Date(), ownDroneResponseDto, httpStatus, path);
        return new ResponseEntity<>(responseDetails, HttpStatus.OK);
    }

    @DeleteMapping("/api/drone-companies/drones/{companyDroneId}")
    @ApiOperation(value = "드론 업체 보유 드론 삭제", notes = "드론 업체가 보유 드론을 삭제합니다.")
    public ResponseEntity<?> deleteOwnDrone(@RequestBody OwnDroneEnrollDto requestDto, HttpServletRequest request, @PathVariable UUID companyDroneId) throws NoResourceException {
        UUIDDto uuidDto = new UUIDDto(droneCompanyOwnDroneService.delete(requestDto, request, companyDroneId));
        String path = "/api/drone-companies/drones/" + companyDroneId;
        int httpStatus = HttpStatusChangeInt.ChangeStatusCode("OK");
        ResponseDetails responseDetails = new ResponseDetails(new Date(), uuidDto, httpStatus, path);
        return new ResponseEntity<>(responseDetails, HttpStatus.OK);
    }

    @GetMapping("/api/drone-companies/drones")
    @ApiOperation(value = "드론 업체 보유 드론 조회", notes = "드론 업체가 보유 드론을 조회합니다.")
    public ResponseEntity<?> showOwnDrone(HttpServletRequest request) throws NoResourceException {
        List<OwnDroneResponseDto> ownDroneResponseDto = droneCompanyOwnDroneService.show(request);
        String path = "/api/drone-companies/drones";
        int httpStatus = HttpStatusChangeInt.ChangeStatusCode("OK");
        ResponseDetails responseDetails = new ResponseDetails(new Date(), ownDroneResponseDto, httpStatus, path);
        return new ResponseEntity<>(responseDetails, HttpStatus.OK);
    }

}
