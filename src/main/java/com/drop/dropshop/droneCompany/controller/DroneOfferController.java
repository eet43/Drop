package com.drop.dropshop.droneCompany.controller;

import com.drop.dropshop.droneCompany.dto.DroneOfferDto;
import com.drop.dropshop.droneCompany.exception.OpenApiResponseErrorException;
import com.drop.dropshop.droneCompany.response.ResponseDetails;
import com.drop.dropshop.droneCompany.service.DroneOfferService;
import com.drop.dropshop.droneCompany.util.HttpStatusChangeInt;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class DroneOfferController {
    private DroneOfferService droneOfferService;
    public DroneOfferController(DroneOfferService droneOfferService) {
        this.droneOfferService = droneOfferService;
    }

    @GetMapping("/api/drones")
    @ApiOperation(value = "드론 목록을 반환", notes = "조건에 따른 선택 가능한 드론 목록을 반환합니다.")
    public ResponseEntity<?> showAllDroneCompany(@QueryParam("weight") double weight,
                                                 @QueryParam("origin") String origin,
                                                 @QueryParam("destination") String destination
                                                 ) throws IOException, OpenApiResponseErrorException {
        List<DroneOfferDto> droneOfferDtoList = droneOfferService.show(weight, origin, destination);
        int httpStatus = HttpStatusChangeInt.ChangeStatusCode("OK");
        String path = "/api/drone-companies";
        ResponseDetails responseDetails = new ResponseDetails(new Date(), droneOfferDtoList, httpStatus, path);
        return new ResponseEntity<>(responseDetails, HttpStatus.OK);
    }
}
