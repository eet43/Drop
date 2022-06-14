package com.drop.dropshop.droneCompany.repository;

import com.drop.dropshop.droneCompany.entity.CompanyDrone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneCompanyOwnDroneRepository extends JpaRepository<CompanyDrone, Long> {
}
