package com.drop.dropshop.droneCompany.service;

import com.drop.dropshop.droneCompany.dto.DroneModelDto;
import com.drop.dropshop.droneCompany.entity.DroneModel;
import com.drop.dropshop.droneCompany.exception.ErrorCode;
import com.drop.dropshop.droneCompany.exception.NoResourceException;
import com.drop.dropshop.droneCompany.repository.DroneModelRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
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

    /**
     * 드론 모델 수정
     */
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

    /**
     * 드론 모델 삭제
     */
    public UUID delete(UUID droneModelId) throws NoResourceException {
        if(droneModelRepository.findByModelId(droneModelId).isEmpty()){
            String errorMessage = "삭제 요청 받은 Drone Model Id : " + droneModelId;
            throw new NoResourceException("삭제 실패", ErrorCode.RESOURCE_NOT_FOUND, errorMessage);
        } else {
            droneModelRepository.deleteByModelId(droneModelId);
        }
        return droneModelId;
    }

    public Page<DroneModel> show(Pageable pageable) {
        return droneModelRepository.findAll(pageable);
    }
}
