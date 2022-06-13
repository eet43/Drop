package com.drop.dropshop.droneCompany.service;

import com.drop.dropshop.droneCompany.dto.DroneCompanyDto;
import com.drop.dropshop.droneCompany.entity.DroneCompany;
import com.drop.dropshop.droneCompany.exception.BusinessNumberNotValidException;
import com.drop.dropshop.droneCompany.exception.NoResourceException;
import com.drop.dropshop.droneCompany.repository.DroneCompanyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class DroneCompanyServiceTest {
    @Autowired
    private DroneCompanyRepository droneCompanyRepository;
    @Autowired
    private DroneCompanyService droneCompanyService;

    @Test
    void 사업자_번호_검증_성공() {
        Boolean result = droneCompanyService.validateBuisenessNumber("3098106517");
        assertEquals(result, true);
    }

    @Test
    void 사업자_번호_검증_실패() {
        Boolean result = droneCompanyService.validateBuisenessNumber("0000000000");
        assertEquals(result, false);
    }

    @Test
    void 드론_업체_등록_실패() throws BusinessNumberNotValidException {
        DroneCompanyDto droneCompanyDto = new DroneCompanyDto(
                "11드론",
                "0313327312",
                "000000000"
        );
        BusinessNumberNotValidException e = assertThrows(BusinessNumberNotValidException.class,
                () -> droneCompanyService.join(droneCompanyDto));
        assertThat(e.getMessage()).isEqualTo("사업자 번호 검증 실패");
    }

    @Test
    void 드론_업체_등록_성공() throws BusinessNumberNotValidException {
        DroneCompanyDto droneCompanyDto = new DroneCompanyDto(
                "11드론",
                "0313327312",
                "3098106517"
        );
        DroneCompany droneCompany = droneCompanyService.join(droneCompanyDto);
        assertEquals(droneCompanyDto.getCompanyName(), droneCompany.getCompanyName());
    }

    @Test
    void 드론_업체_조회() throws BusinessNumberNotValidException {
        DroneCompanyDto droneCompanyDto = new DroneCompanyDto(
                "11드론",
                "0313327312",
                "3098106517"
        );
        DroneCompany droneCompany = droneCompanyService.join(droneCompanyDto);
        Pageable pageable =  PageRequest.of(0, 10, Sort.by("createdAt").descending());
        Page<DroneCompany> droneCompanies = droneCompanyService.show(pageable);
        droneCompany = droneCompanies.getContent().get(0);
        assertEquals(droneCompanyDto.getCompanyName(), droneCompany.getCompanyName());
    }

    @Test
    void 드론_업체_삭제() throws BusinessNumberNotValidException, NoResourceException {
        DroneCompanyDto droneCompanyDto = new DroneCompanyDto(
                "11드론",
                "0313327312",
                "3098106517"
        );
        DroneCompany droneCompany = droneCompanyService.join(droneCompanyDto);
        UUID result = droneCompanyService.delete(droneCompany.getCompanyId());
        assertEquals(droneCompany.getCompanyId(), result);
    }
}