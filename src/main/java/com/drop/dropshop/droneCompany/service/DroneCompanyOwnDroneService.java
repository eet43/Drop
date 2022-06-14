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
     * 드론 업체의 보유 드론 등록
     */
    public OwnDroneResponseDto enroll(OwnDroneEnrollDto requestDto, HttpServletRequest request) throws NoResourceException {
        // 드론 업체 식별 정보 가져오기
        DroneCompany droneCompany = authenticationDroneCompany(request);

        Optional<DroneModel> droneModel = droneModelRepository.findByModelId(requestDto.getModelId());
        if (droneModel.isEmpty()) {
            String errorMessage = "등록 요청 받은 Drone Model Id : " + requestDto.getModelId();
            throw new NoResourceException("드론 모델 검색 불가", ErrorCode.RESOURCE_NOT_FOUND, errorMessage);
        }

        // 드론 업체의 보유 드론 목록 저장
        CompanyDrone companyDrone = new CompanyDrone(droneCompany.getCompanyId(),
                requestDto.getModelId(),
                requestDto.getBasePrice(),
                requestDto.getNum(),
                requestDto.getOperableNum());

        UUID companyDroneId = UUID.randomUUID();
        companyDrone.setCompanyDroneId(companyDroneId);

        droneCompanyOwnDroneRepository.save(companyDrone);

        List<CompanyDroneDetail> companyDroneDetailList = new ArrayList<>();

        // 드론 업체의 상세 드론 정보 저장
        for (DroneDetailDto companyDroneDetailDtoList : requestDto.getCompanyDroneDetailList()) {
            CompanyDroneDetail companyDroneDetail = new CompanyDroneDetail(companyDroneId,
                    companyDroneDetailDtoList.getSerialNumber(),
                    companyDroneDetailDtoList.getStatusCode(),
                    companyDroneDetailDtoList.getRaceCode());
            UUID droneId = UUID.randomUUID();
            companyDroneDetail.setDroneId(droneId);
            droneCompanyOwnDroneDetailRepository.save(companyDroneDetail);
            companyDroneDetailList.add(companyDroneDetail);
        }

        return new OwnDroneResponseDto(
                droneCompany,
                droneModel.get(),
                companyDrone.getNum(),
                companyDrone.getOperableNum(),
                companyDroneDetailList
        );
    }
}
