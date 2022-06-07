package com.drop.dropshop.droneCompany.repository;

import com.drop.dropshop.droneCompany.entity.DroneCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DroneCompanyRepository extends JpaRepository<DroneCompany, Long> {
    Optional<DroneCompany> findByBuisenessNumber(String buisenessNumber);

    Optional<DroneCompany> findByLoginId(String loginId);
}
