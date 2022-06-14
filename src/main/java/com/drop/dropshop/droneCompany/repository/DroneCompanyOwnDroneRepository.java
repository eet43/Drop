package com.drop.dropshop.droneCompany.repository;

import com.drop.dropshop.droneCompany.entity.CompanyDrone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DroneCompanyOwnDroneRepository extends JpaRepository<CompanyDrone, Long> {
    Optional<CompanyDrone> findByCompanyIdAndCompanyDroneId(UUID companyId, UUID companyDroneId);

    void deleteByCompanyDroneId(UUID companyDroneId);

    List<CompanyDrone> findAllByCompanyId(UUID companyId);
}
