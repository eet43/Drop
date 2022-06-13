package com.drop.dropshop.droneCompany.repository;

import com.drop.dropshop.droneCompany.entity.DroneModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DroneModelRepository extends JpaRepository<DroneModel, Long> {
    Optional<DroneModel> findByModelName(String modelName);

    Optional<DroneModel> findByModelId(UUID droneModelId);

    void deleteByModelId(UUID droneModelId);
}
