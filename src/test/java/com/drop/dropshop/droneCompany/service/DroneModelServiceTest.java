package com.drop.dropshop.droneCompany.service;

import com.drop.dropshop.droneCompany.dto.DroneModelDto;
import com.drop.dropshop.droneCompany.entity.DroneModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@Transactional
class DroneModelServiceTest {
    @Autowired
    private DroneModelService droneModelService;

    @Test
    void enroll() {
        DroneModelDto droneModelDto = new DroneModelDto("샤오미 TEST용 드론", 2, 52, 30, false, true, -10, 20);
        DroneModel droneModel = droneModelService.enroll(droneModelDto);
        assertEquals(droneModelDto.getModelName(), droneModel.getModelName());
    }
}