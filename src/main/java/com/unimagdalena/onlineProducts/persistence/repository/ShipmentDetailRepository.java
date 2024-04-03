package com.unimagdalena.onlineProducts.persistence.repository;

import com.unimagdalena.onlineProducts.persistence.entity.OrderItemEntity;
import com.unimagdalena.onlineProducts.persistence.entity.ShipmentDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShipmentDetailRepository extends JpaRepository<ShipmentDetailEntity, Long> {
    List<ShipmentDetailEntity> findAllByOrderId(int orderId);
    List<ShipmentDetailEntity> findAllByCarrier(String carrier);
    boolean existsByIdDetail(int id);
    ShipmentDetailEntity findByIdDetail(int id);




}
