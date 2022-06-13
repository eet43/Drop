package com.drop.dropshop.droneCompany.service;

import com.drop.dropshop.droneCompany.dto.DroneModelDto;
import com.drop.dropshop.droneCompany.entity.DroneModel;
import com.drop.dropshop.droneCompany.repository.DroneCompanyRepository;
import com.drop.dropshop.droneCompany.repository.DroneModelRepository;
import org.springframework.stereotype.Service;

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

}
