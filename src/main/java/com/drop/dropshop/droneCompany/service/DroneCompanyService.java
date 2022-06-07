package com.drop.dropshop.droneCompany.service;

import com.drop.dropshop.droneCompany.dto.DroneCompanyDto;
import com.drop.dropshop.droneCompany.entity.DroneCompany;
import com.drop.dropshop.droneCompany.exception.BusinessNumberNotValidException;
import com.drop.dropshop.droneCompany.exception.ErrorCode;
import com.drop.dropshop.droneCompany.repository.DroneCompanyRepository;
import okhttp3.*;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.UUID;

@Service
@Transactional
public class DroneCompanyService {
    private DroneCompanyRepository droneCompanyRepository;

    public DroneCompanyService(DroneCompanyRepository droneCompanyRepository) {
        this.droneCompanyRepository = droneCompanyRepository;
    }

    /**
     * 드론 업체 사업자 번호 유효성 검증
     * 국세청_사업자등록정보 진위확인 및 상태조회 서비스 오픈 API 이용
     */
    public boolean validateBuisenessNumber(String buisenessNumber) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType Json = MediaType.parse("application/json");
        String sentence = String.format("{\n  \"b_no\": [\n    \"%s\"\n  ]\n}", buisenessNumber);
        RequestBody body = RequestBody.create(Json, sentence);
        Request request = new Request.Builder()
                .url("http://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=hwT2FgxZA9b2VxPJCLLaqHHzo%2FlqHHAOptIWwtxlVFVVUilDq0OnYmXSuSlQSoOg1NxYnke2XPB2Cr%2BNQEhMpA%3D%3D")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            System.out.println(responseBody);
            JSONObject jsonObject = new JSONObject(responseBody);
            if (jsonObject.has("match_cnt")) {
                return true;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * 드론 업체 중복 검사
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
        String number = String.valueOf((int) (Math.random() * 9999 + 1000));
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
    public DroneCompany join(DroneCompanyDto droneCompanyDto) throws BusinessNumberNotValidException {
        validateDuplicateCompany(droneCompanyDto); // 중복 회원 검증
        DroneCompany droneCompany = new DroneCompany(droneCompanyDto);

        // 사업자 번호 검증
        if (!validateBuisenessNumber(droneCompany.getBuisenessNumber())) {
            String errorMessage = "요청 받은 사업자 번호 : " + droneCompany.getBuisenessNumber();
            throw new BusinessNumberNotValidException("사업자 번호 검증 실패", ErrorCode.BUSINESS_NUMBER_NOT_VALID, errorMessage);
        }

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