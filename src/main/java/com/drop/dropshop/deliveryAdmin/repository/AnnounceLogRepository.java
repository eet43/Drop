package com.drop.dropshop.deliveryAdmin.repository;


import com.drop.dropshop.deliveryAdmin.entity.AnnounceLog;
//import com.drop.dropshop.deliveryAdmin.entity.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnnounceLogRepository extends JpaRepository<AnnounceLog, Long> {
}