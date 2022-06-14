package com.drop.dropshop.droneCompany.repository;

import com.drop.dropshop.droneCompany.entity.CompanyDroneDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface DroneCompanyOwnDroneDetailRepository extends JpaRepository<CompanyDroneDetail, Long> {
    List<CompanyDroneDetail> findAllByCompanyDroneId(UUID companyId);

    void deleteAllByCompanyDroneId(UUID companyId);
}
