package com.unimagdalena.onlineProducts.persistence.mapper;

import com.unimagdalena.onlineProducts.persistence.DTO.ShipmentDetailDto;
import com.unimagdalena.onlineProducts.persistence.entity.ShipmentDetailEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShipmentDetailMapper {

    ShipmentDetailEntity shipmentDetailDtoToShipmentDetailEntity(ShipmentDetailDto shipmentDetailDto);
    ShipmentDetailDto ShipmentDetailEntityToShipmentDetailDto (ShipmentDetailEntity shipmentDetailEntity);

}
