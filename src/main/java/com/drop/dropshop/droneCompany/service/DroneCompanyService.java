package com.drop.dropshop.droneCompany.service;

import com.drop.dropshop.droneCompany.dto.DroneCompanyDto;
import com.drop.dropshop.droneCompany.entity.DroneCompany;
import com.drop.dropshop.droneCompany.repository.DroneCompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.UUID;

@Service
@Transactional
public class DroneCompanyService {
    private DroneCompanyRepository droneCompanyRepository;

    public DroneCompanyService(DroneCompanyRepository droneCompanyRepository) {
        this.droneCompanyRepository = droneCompanyRepository;
    }

    /**
     * 드론 업체 유효성 검사
     */
    private void validateDuplicateCompany(DroneCompanyDto droneCompanyDto) {
        droneCompanyRepository.findByBuisenessNumber(droneCompanyDto.getBuisenessNumber())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 업체입니다.");
                });
    }

    /**
     * 드론 업체 로그인 아이디 중복 체크
     * 중복 시 새로운 (1000 ~ 9999)사이의 랜덤 숫자를 생성하여 로그인 아이디 생성
     */
    private String validateDuplicateId(String loginId) {
        String number = String.valueOf((int)(Math.random() * 9999 + 1000));
        droneCompanyRepository.findByLoginId(loginId)
                .ifPresent(m -> {
                    createLoginId(loginId, number);
                });
        return loginId;
    }

    /**
     * 드론 업체 로그인 아이디 생성
     * 아이디 : 업체 이름 + 전화번호 뒷 4자리
     * ex) 업체 이름 : 스파르타, 업체 전화번호 : 031-000-7781 → 로그인 아이디 : 스파르타7781
     */
    private String createLoginId(String companyName, String companyContract) {
        String newId = companyName + companyContract.substring(companyContract.length() - 4, companyContract.length());
        return validateDuplicateId(newId);
    }

    /**
     * 드론 업체 등록
     */
    public DroneCompany join(DroneCompanyDto droneCompanyDto) {
        validateDuplicateCompany(droneCompanyDto); // 중복 회원 검증
        DroneCompany droneCompany = new DroneCompany(droneCompanyDto);
        // uuid 생성
        UUID uuid = UUID.randomUUID();
        droneCompany.setCompanyId(uuid);
        // 로그인 아이디 생성
        String loginId = createLoginId(droneCompanyDto.getCompanyName(), droneCompanyDto.getCompanyContract());
        droneCompany.setLoginId(loginId);

        droneCompanyRepository.save(droneCompany);
        return droneCompany;
    }


}
