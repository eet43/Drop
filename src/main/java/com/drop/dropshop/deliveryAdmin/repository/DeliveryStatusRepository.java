package com.drop.dropshop.deliveryAdmin.repository;

import com.drop.dropshop.deliveryAdmin.entity.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryStatusRepository extends JpaRepository<DeliveryStatus, String> {

}
