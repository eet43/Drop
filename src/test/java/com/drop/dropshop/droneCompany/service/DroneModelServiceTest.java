package com.drop.dropshop.droneCompany.service;

import com.drop.dropshop.droneCompany.dto.DroneModelDto;
import com.drop.dropshop.droneCompany.entity.DroneModel;
import com.drop.dropshop.droneCompany.exception.NoResourceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@Transactional
class DroneModelServiceTest {
    @Autowired
    private DroneModelService droneModelService;

    @Test
    DroneModel 드론_모델_등록() {
        DroneModelDto droneModelDto = new DroneModelDto("샤오미 TEST용 드론", 2, 52, 30, false, true, -10, 20);
        DroneModel droneModel = droneModelService.enroll(droneModelDto);
        assertEquals(droneModelDto.getModelName(), droneModel.getModelName());
        return droneModel;
    }

    @Test
    void 드론_모델_수정() throws NoResourceException {
        DroneModel droneModel = 드론_모델_등록();
        DroneModelDto droneModelDto = new DroneModelDto("샤오미 TEST 수정용 드론", 21, 52, 30, false, false, -100, 200);
        droneModelService.update(droneModelDto, droneModel.getModelId());
        assertEquals(droneModelDto.getModelName(), droneModel.getModelName());
    }

    @Test
    void 드론_모델_삭제() throws NoResourceException {
        UUID droneModelId = 드론_모델_등록().getModelId();
        UUID result = droneModelService.delete(droneModelId);
        assertEquals(result, droneModelId);
    }

    @Test
    void 드론_모델_조회() {
        Pageable pageable =  PageRequest.of(1, 10, Sort.by("createdAt").descending());
        Page<DroneModel> droneModelList = droneModelService.show(pageable);
        assertTrue(droneModelList.getTotalElements() >= 0 && droneModelList.getTotalElements() <= 10);
    }
}