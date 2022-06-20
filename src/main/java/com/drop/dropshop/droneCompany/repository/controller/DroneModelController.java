package com.drop.dropshop.droneCompany.repository.controller;

import com.drop.dropshop.droneCompany.dto.DroneCompanyDto;
import com.drop.dropshop.droneCompany.dto.DroneModelDto;
import com.drop.dropshop.droneCompany.dto.UUIDDto;
import com.drop.dropshop.droneCompany.entity.DroneCompany;
import com.drop.dropshop.droneCompany.entity.DroneModel;
import com.drop.dropshop.droneCompany.exception.BusinessNumberNotValidException;
import com.drop.dropshop.droneCompany.exception.NoResourceException;
import com.drop.dropshop.droneCompany.response.PagingResponseDetails;
import com.drop.dropshop.droneCompany.response.ResponseDetails;
import com.drop.dropshop.droneCompany.service.DroneModelService;
import com.drop.dropshop.droneCompany.util.HttpStatusChangeInt;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    @PutMapping("/api/drone-models/{id}")
    @ApiOperation(value = "드론 모델 수정", notes = "관리자가 드론 모델을 등록합니다.")
    public ResponseEntity<?> updateDroneModel(@RequestBody DroneModelDto requestDto, @PathVariable UUID id) throws NoResourceException {
        DroneModel droneModel = droneModelService.update(requestDto, id);
        String path = "/api/drone-models/" + droneModel.getModelId();
        int httpStatus = HttpStatusChangeInt.ChangeStatusCode("OK");
        ResponseDetails responseDetails = new ResponseDetails(new Date(), droneModel, httpStatus, path);
        return new ResponseEntity<>(responseDetails, HttpStatus.OK);
    }

    @DeleteMapping("/api/drone-models/{id}")
    @ApiOperation(value = "드론 모델 삭제", notes = "관리자가 드론 모델을 삭제합니다.")
    public ResponseEntity<?> deleteDroneModel(@PathVariable UUID id) throws NoResourceException {
        UUID result = droneModelService.delete(id);
        UUIDDto uuidDto = new UUIDDto(result);
        String path = "/api/drone-models/" + id;
        int httpStatus = HttpStatusChangeInt.ChangeStatusCode("OK");
        ResponseDetails responseDetails = new ResponseDetails(new Date(), uuidDto, httpStatus, path);
        return new ResponseEntity<>(responseDetails, HttpStatus.OK);
    }

    @GetMapping("/api/drone-models")
    @ApiOperation(value = "드론 모델 조회", notes = "관리자가 드론 모델을 조회합니다.")
    public ResponseEntity<?> deleteDroneModel(@PageableDefault(page = 1, size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<DroneModel> droneModelList = droneModelService.show(pageable);
        String path = "/api/drone-models";
        int httpStatus = HttpStatusChangeInt.ChangeStatusCode("OK");
        PagingResponseDetails pagingResponseDetails = new PagingResponseDetails(new Date(),
                droneModelList.getContent(),
                droneModelList.getTotalElements(),
                droneModelList.getPageable().getPageNumber() + 1,
                droneModelList.getPageable().getPageSize(),
                httpStatus, path);
        ResponseDetails responseDetails = new ResponseDetails(new Date(), pagingResponseDetails, httpStatus, path);
        return new ResponseEntity<>(responseDetails, HttpStatus.OK);
    }
}
