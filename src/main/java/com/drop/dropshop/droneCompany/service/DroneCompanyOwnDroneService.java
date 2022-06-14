package com.drop.dropshop.droneCompany.service;

import com.drop.dropshop.droneCompany.dto.DroneDetailDto;
import com.drop.dropshop.droneCompany.dto.OwnDroneEnrollDto;
import com.drop.dropshop.droneCompany.dto.OwnDroneResponseDto;
import com.drop.dropshop.droneCompany.entity.CompanyDrone;
import com.drop.dropshop.droneCompany.entity.CompanyDroneDetail;
import com.drop.dropshop.droneCompany.entity.DroneCompany;
import com.drop.dropshop.droneCompany.entity.DroneModel;
import com.drop.dropshop.droneCompany.exception.ErrorCode;
import com.drop.dropshop.droneCompany.exception.NoResourceException;
import com.drop.dropshop.droneCompany.repository.DroneCompanyOwnDroneDetailRepository;
import com.drop.dropshop.droneCompany.repository.DroneCompanyOwnDroneRepository;
import com.drop.dropshop.droneCompany.repository.DroneModelRepository;
import com.drop.dropshop.droneCompany.security.DroneDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestAttribute;

import javax.servlet.http.HttpServletRequest;
import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class DroneCompanyOwnDroneService {
    private final DroneCompanyOwnDroneDetailRepository droneCompanyOwnDroneDetailRepository;
    private final DroneCompanyOwnDroneRepository droneCompanyOwnDroneRepository;
    private final DroneModelRepository droneModelRepository;

    /**
     * 요청으로 받아온 인증 정보에서 DroneCompany 정보를 찾아 return
     */
    public DroneCompany authenticationDroneCompany(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        DroneDetailsImpl droneDetails = (DroneDetailsImpl) authentication.getPrincipal();
        return droneDetails.getDroneCompany();
    }

    /**
     * Drone Model ID 로 Drone Model 식별
     */
    public DroneModel findByModelId(UUID modelId) throws NoResourceException {
        Optional<DroneModel> droneModel = droneModelRepository.findByModelId(modelId);
        if (droneModel.isEmpty()) {
            String errorMessage = "검색 요청 받은 Drone Model Id : " + modelId;
            throw new NoResourceException("드론 모델 검색 불가", ErrorCode.RESOURCE_NOT_FOUND, errorMessage);
        }
        return droneModel.get();
    }

    /**
     * droneDetailDto 로 이루어진 List 를 반복하여 company_own_drone save 진행
     */
    public List<CompanyDroneDetail> saveCompanyDroneDetailList(List<DroneDetailDto> droneDetailDtoList, UUID companyDroneId) {
        List<CompanyDroneDetail> companyDroneDetailList = new ArrayList<>();

        // 드론 업체의 상세 드론 정보 저장
        for (DroneDetailDto companyDroneDetailDtoList : droneDetailDtoList) {
            CompanyDroneDetail companyDroneDetail = new CompanyDroneDetail(companyDroneId,
                    companyDroneDetailDtoList.getSerialNumber(),
                    companyDroneDetailDtoList.getStatusCode(),
                    companyDroneDetailDtoList.getRaceCode());
            UUID droneId = UUID.randomUUID();
            companyDroneDetail.setDroneId(droneId);
            droneCompanyOwnDroneDetailRepository.save(companyDroneDetail);
            companyDroneDetailList.add(companyDroneDetail);
        }
        return companyDroneDetailList;
    }


    /**
     * 드론 업체의 보유 드론 조회 --> company_drone 테이블
     */
    public CompanyDrone validationCompanyDrone(UUID companyId, UUID companyDroneId) throws NoResourceException {
        Optional<CompanyDrone> companyDroneOptional = droneCompanyOwnDroneRepository.findByCompanyIdAndCompanyDroneId(companyId, companyDroneId);
        if (companyDroneOptional.isEmpty()) {
            String errorMessage = "수정 요청 받은 Company Drone Id : " + companyDroneId;
            throw new NoResourceException("드론 업체의 보유 드론 목록 조회 실패", ErrorCode.RESOURCE_NOT_FOUND, errorMessage);
        } else {
            return companyDroneOptional.get();
        }
    }

    /**
     * 드론 업체의 보유 드론 상세 조회 --> company_own_drone 테이블
     */
    public void validationCompanyDroneDetail(UUID companyDroneId) throws NoResourceException {
        if (droneCompanyOwnDroneDetailRepository.findAllByCompanyDroneId(companyDroneId).isEmpty()) {
            String errorMessage = "수정 요청 받은 Company Drone Id : " + companyDroneId;
            throw new NoResourceException("수정 실패", ErrorCode.RESOURCE_NOT_FOUND, errorMessage);
        }
    }

    /**
     * 드론 업체의 보유 드론 등록
     */
    public OwnDroneResponseDto enroll(OwnDroneEnrollDto requestDto, HttpServletRequest request) throws NoResourceException {
        // 드론 업체 식별 정보 가져오기
        DroneCompany droneCompany = authenticationDroneCompany(request);

        // 드론 모델 정보 가져오기
        DroneModel droneModel = findByModelId(requestDto.getModelId());

        // 드론 업체의 보유 드론 목록 저장
        CompanyDrone companyDrone = new CompanyDrone(droneCompany.getCompanyId(),
                requestDto.getModelId(),
                requestDto.getBasePrice(),
                requestDto.getNum(),
                requestDto.getOperableNum());

        UUID companyDroneId = UUID.randomUUID();
        companyDrone.setCompanyDroneId(companyDroneId);

        droneCompanyOwnDroneRepository.save(companyDrone);

        List<CompanyDroneDetail> companyDroneDetailList = saveCompanyDroneDetailList(
                requestDto.getCompanyDroneDetailList(), companyDroneId);

        return new OwnDroneResponseDto(
                companyDroneId,
                companyDrone.getNum(),
                companyDrone.getOperableNum(),
                droneCompany,
                droneModel,
                companyDroneDetailList
        );
    }

    /**
     * 드론 업체 보유 드론 수정
     */
    public OwnDroneResponseDto update(OwnDroneEnrollDto requestDto, HttpServletRequest request, UUID companyDroneId) throws NoResourceException {
        DroneCompany droneCompany = authenticationDroneCompany(request);

        // company_drone 테이블에 해당 데이터는 있지만 company_own_drone 테이블에 해당 데이터가 없는 경우 검증
        validationCompanyDroneDetail(companyDroneId);

        droneCompanyOwnDroneDetailRepository.deleteAllByCompanyDroneId(companyDroneId);
        droneCompanyOwnDroneDetailRepository.flush();

        // 업체의 드론 식별 후 업데이트
        CompanyDrone companyDrone = validationCompanyDrone(droneCompany.getCompanyId(), companyDroneId);
        companyDrone.update(requestDto);

        // 업체의 드론 모델 식별
        DroneModel droneModel = findByModelId(companyDrone.getModelId());

        List<CompanyDroneDetail> companyDroneDetailList = saveCompanyDroneDetailList(
                requestDto.getCompanyDroneDetailList(), companyDroneId);

        return new OwnDroneResponseDto(
                companyDroneId,
                companyDrone.getNum(),
                companyDrone.getOperableNum(),
                droneCompany,
                droneModel,
                companyDroneDetailList
        );

    }

    /**
     * 드론 업체 보유 드론 삭제
     */
    public UUID delete(OwnDroneEnrollDto requestDto, HttpServletRequest request, UUID companyDroneId) throws NoResourceException {
        DroneCompany droneCompany = authenticationDroneCompany(request);
        // 업체의 드론 식별
        CompanyDrone companyDrone = validationCompanyDrone(droneCompany.getCompanyId(), companyDroneId);
        // company_drone 테이블에 해당 데이터는 있지만 company_own_drone 테이블에 해당 데이터가 없는 경우 검증
        validationCompanyDroneDetail(companyDroneId);
        droneCompanyOwnDroneRepository.deleteByCompanyDroneId(companyDroneId);
        droneCompanyOwnDroneDetailRepository.deleteAllByCompanyDroneId(companyDroneId);
        return companyDroneId;
    }
}
