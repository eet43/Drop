package com.drop.dropshop.droneCompany.repository;

import com.drop.dropshop.droneCompany.entity.DroneCompany;
import com.drop.dropshop.droneCompany.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DroneCompanyRefreshTokenRepository extends JpaRepository<RefreshToken, Long>  {
    Optional<RefreshToken> findRefreshTokenByIdx(long refreshIdx);
}
