package com.unimagdalena.onlineProducts.service;

import com.unimagdalena.onlineProducts.persistence.DTO.ShipmentDetailDto;
import com.unimagdalena.onlineProducts.persistence.entity.ShipmentDetailEntity;

import java.util.List;

public interface ShipmentDetailService {
    List<ShipmentDetailDto> getAll();
    List<ShipmentDetailDto> findAllByOrderId(int orderId);
    List<ShipmentDetailDto> findAllByCarrier(String carrier);
    boolean existsByIdDetail(int id);
    ShipmentDetailDto findByIdDetail(int id);

    ShipmentDetailEntity save(ShipmentDetailEntity shipmentDetailEntity);
    ShipmentDetailEntity update(int id, ShipmentDetailDto shipmentDetailDto);
    void  delete (int id);

}
