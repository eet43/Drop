package com.drop.dropshop.droneCompany.service;

import com.drop.dropshop.droneCompany.dto.OwnDroneResponseDto;
import com.drop.dropshop.droneCompany.entity.*;
import com.drop.dropshop.droneCompany.exception.ErrorCode;
import com.drop.dropshop.droneCompany.exception.NoResourceException;
import com.drop.dropshop.droneCompany.repository.DroneCompanyOwnDroneDetailRepository;
import com.drop.dropshop.droneCompany.repository.DroneCompanyOwnDroneRepository;
import com.drop.dropshop.droneCompany.repository.DroneCompanyRepository;
import com.drop.dropshop.droneCompany.repository.DroneModelRepository;
import com.drop.dropshop.droneCompany.security.DroneDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@Transactional
public class DroneDeliveryService {
    private DroneCompanyOwnDroneDetailRepository droneCompanyOwnDroneDetailRepository;
    private DroneCompanyOwnDroneRepository droneCompanyOwnDroneRepository;
    private DroneModelRepository droneModelRepository;

    @Autowired
    public DroneDeliveryService(DroneCompanyOwnDroneDetailRepository droneCompanyOwnDroneDetailRepository
            , DroneCompanyOwnDroneRepository droneCompanyOwnDroneRepository
            , DroneModelRepository droneModelRepository) {
        this.droneCompanyOwnDroneDetailRepository = droneCompanyOwnDroneDetailRepository;
        this.droneCompanyOwnDroneRepository = droneCompanyOwnDroneRepository;
        this.droneModelRepository = droneModelRepository;
    }

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
     * 드론 운행 상태 변경
     */
    public OwnDroneResponseDto update(Map<String, String> requestObject, UUID droneId, HttpServletRequest request) throws NoResourceException {
        Optional<CompanyDroneDetail> companyDroneDetailOptional = droneCompanyOwnDroneDetailRepository.findByDroneId(droneId);
        if (companyDroneDetailOptional.isEmpty()) {
            String errorMessage = "상태 변경 요청 받은 Drone Id : " + droneId;
            throw new NoResourceException("드론 검색 불가", ErrorCode.RESOURCE_NOT_FOUND, errorMessage);
        }
        CompanyDroneDetail companyDroneDetail = companyDroneDetailOptional.get();
        Optional<CompanyDrone> companyDroneOptional = droneCompanyOwnDroneRepository.findByCompanyDroneId(companyDroneDetail.getCompanyDroneId());
        if (companyDroneOptional.isEmpty()) {
            String errorMessage = "상태 변경 요청 받은 Drone Id : " + droneId;
            throw new NoResourceException("상위 드론 목록 검색 불가", ErrorCode.RESOURCE_NOT_FOUND, errorMessage);
        }
        String raceStatus = requestObject.get("raceStatus");
        CompanyDrone companyDrone = companyDroneOptional.get();
        if (RaceCode.valueOf(raceStatus).equals(RaceCode.WAITING)) {
            companyDrone.updateOperableNum(companyDrone.getOperableNum() + 1);
        } else {
            companyDrone.updateOperableNum(companyDrone.getOperableNum() - 1);
        }
        companyDroneDetail.updateRaceStatus(RaceCode.valueOf(raceStatus));
        ArrayList<CompanyDroneDetail> companyDroneDetailArrayList = new ArrayList<>();
        companyDroneDetailArrayList.add(companyDroneDetail);

        DroneCompany droneCompany = authenticationDroneCompany(request);
        DroneModel droneModel = findByModelId(companyDrone.getModelId());
        return new OwnDroneResponseDto(
                companyDrone.getCompanyDroneId(),
                companyDrone.getNum(),
                companyDrone.getOperableNum(),
                droneCompany,
                droneModel,
                companyDroneDetailArrayList
        );
    }

    /**
     * 드론 위치 전달 -> 이후 메세지 큐 구현
     */
    public void location(Map<String, String> requestObject, UUID droneId) {
        //메세지 큐 적용해보기
    }
}
