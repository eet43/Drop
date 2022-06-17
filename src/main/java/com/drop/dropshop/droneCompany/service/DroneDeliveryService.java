package com.drop.dropshop.droneCompany.service;

import com.drop.dropshop.droneCompany.entity.CompanyDroneDetail;
import com.drop.dropshop.droneCompany.entity.RaceCode;
import com.drop.dropshop.droneCompany.exception.ErrorCode;
import com.drop.dropshop.droneCompany.exception.NoResourceException;
import com.drop.dropshop.droneCompany.repository.DroneCompanyOwnDroneDetailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@Transactional
public class DroneDeliveryService {
    private DroneCompanyOwnDroneDetailRepository droneCompanyOwnDroneDetailRepository;

    public DroneDeliveryService(DroneCompanyOwnDroneDetailRepository droneCompanyOwnDroneDetailRepository){
        this.droneCompanyOwnDroneDetailRepository = droneCompanyOwnDroneDetailRepository;
    }

    /**
     * 드론 운행 상태 변경
     */
    public CompanyDroneDetail update(Map<String, String> requestObject, UUID droneId) throws NoResourceException {
        Optional<CompanyDroneDetail> companyDroneDetailOptional = droneCompanyOwnDroneDetailRepository.findByDroneId(droneId);
        if(companyDroneDetailOptional.isEmpty()) {
            String errorMessage = "상태 변경 요청 받은 Drone Id : " + droneId;
            throw new NoResourceException("드론 검색 불가", ErrorCode.RESOURCE_NOT_FOUND, errorMessage);
        }
        CompanyDroneDetail companyDroneDetail = companyDroneDetailOptional.get();
        String raceStatus = requestObject.get("raceStatus");
        companyDroneDetail.updateRaceStatus(RaceCode.valueOf(raceStatus));
        return companyDroneDetail;
    }
}
