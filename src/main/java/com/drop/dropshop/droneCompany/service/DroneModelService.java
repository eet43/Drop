package com.drop.dropshop.droneCompany.service;

import com.drop.dropshop.droneCompany.dto.DroneModelDto;
import com.drop.dropshop.droneCompany.entity.DroneModel;
import com.drop.dropshop.droneCompany.exception.ErrorCode;
import com.drop.dropshop.droneCompany.exception.NoResourceException;
import com.drop.dropshop.droneCompany.repository.DroneCompanyRepository;
import com.drop.dropshop.droneCompany.repository.DroneModelRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DroneModelService {
    private DroneModelRepository droneModelRepository;

    public DroneModelService(DroneModelRepository droneModelRepository){
        this.droneModelRepository = droneModelRepository;
    }

    /**
     * 드론 모델 중복 검사
     */
    private void validateDuplicateModel(String modelName) {
        droneModelRepository.findByModelName(modelName)
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 모델입니다.");
                });
    }

    /**
     * 드론 모델 등록
     */
    public DroneModel enroll(DroneModelDto requestDto) {
        validateDuplicateModel(requestDto.getModelName());
        DroneModel droneModel = new DroneModel(requestDto);

        // uuid 생성
        UUID uuid = UUID.randomUUID();
        droneModel.setModelId(uuid);

        droneModelRepository.save(droneModel);
        return droneModel;
    }

    public DroneModel update(DroneModelDto requestDto, UUID droneModelId) throws NoResourceException {
        Optional<DroneModel> droneModelOptional = droneModelRepository.findByModelId(droneModelId);
        if(droneModelOptional.isEmpty()){
            String errorMessage = "수정 요청 받은 Drone Model Id : " + droneModelId;
            throw new NoResourceException("수정 실패", ErrorCode.RESOURCE_NOT_FOUND, errorMessage);
        }
        DroneModel droneModel = droneModelOptional.get();
        droneModel.update(requestDto);
        return droneModel;
    }
}
