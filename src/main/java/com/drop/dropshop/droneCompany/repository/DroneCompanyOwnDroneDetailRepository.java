package com.drop.dropshop.droneCompany.repository;

import com.drop.dropshop.droneCompany.entity.CompanyDroneDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneCompanyOwnDroneDetailRepository extends JpaRepository<CompanyDroneDetail, Long> {
}
