package com.drop.dropshop.droneCompany.controller;

import com.drop.dropshop.droneCompany.dto.DroneCompanyDto;
import com.drop.dropshop.droneCompany.dto.UUIDDto;
import com.drop.dropshop.droneCompany.entity.DroneCompany;
import com.drop.dropshop.droneCompany.exception.BusinessNumberNotValidException;
import com.drop.dropshop.droneCompany.exception.NoResourceException;
import com.drop.dropshop.droneCompany.response.PagingResponseDetails;
import com.drop.dropshop.droneCompany.response.ResponseDetails;
import com.drop.dropshop.droneCompany.service.DroneCompanyService;
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
import java.util.UUID;

@RestController
@Slf4j
public class DroneCompanyController {
    private DroneCompanyService droneCompanyService;

    public DroneCompanyController(DroneCompanyService droneCompanyService) {
        this.droneCompanyService = droneCompanyService;
    }

    @PostMapping("/api/admin/drone-companies")
    @ApiOperation(value = "드론 업체 등록", notes = "관리자가 드론 업체를 등록합니다.")
    public ResponseEntity<?> createDroneCompany(@RequestBody DroneCompanyDto requestDto) throws BusinessNumberNotValidException {
        DroneCompany droneCompany = droneCompanyService.join(requestDto);
        String path = "/api/drone-companies/" + droneCompany.getCompanyId();
        int httpStatus = HttpStatusChangeInt.ChangeStatusCode("CREATED");
        ResponseDetails responseDetails = new ResponseDetails(new Date(), droneCompany, httpStatus, path);
        return new ResponseEntity<>(responseDetails, HttpStatus.CREATED);
    }

    @GetMapping("/api/admin/drone-companies")
    @ApiOperation(value = "드론 업체 리스트 조회", notes = "관리자가 드론 업체를 조회합니다.")
    public ResponseEntity<?> showAllDroneCompany(@PageableDefault(page = 1, size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<DroneCompany> droneCompanyList = droneCompanyService.show(pageable);
        int httpStatus = HttpStatusChangeInt.ChangeStatusCode("OK");
        String path = "/api/drone-companies";
        PagingResponseDetails pagingResponseDetails = new PagingResponseDetails(new Date(),
                droneCompanyList.getContent(),
                droneCompanyList.getTotalElements(),
                droneCompanyList.getPageable().getPageNumber() + 1,
                droneCompanyList.getPageable().getPageSize(),
                httpStatus, path);
        return new ResponseEntity<>(pagingResponseDetails, HttpStatus.OK);
    }

    @DeleteMapping("/api/admin/drone-companies/{id}")
    @ApiOperation(value = "드론 업체 삭제", notes = "관리자가 드론 업체를 삭제합니다.")
    public ResponseEntity<?> deleteDroneCompany(@PathVariable UUID id) throws NoResourceException {
        UUID result = droneCompanyService.delete(id);
        UUIDDto uuidDto = new UUIDDto(result);
        int httpStatus = HttpStatusChangeInt.ChangeStatusCode("OK");
        String path = "/api/drone-companies";
        ResponseDetails responseDetails = new ResponseDetails(new Date(), uuidDto, httpStatus, path);
        return new ResponseEntity<>(responseDetails, HttpStatus.OK);
    }
}
