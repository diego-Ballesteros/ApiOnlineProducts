package com.unimagdalena.onlineProducts.persistence.mapper;

import com.unimagdalena.onlineProducts.persistence.DTO.OrderDto;
import com.unimagdalena.onlineProducts.persistence.entity.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto orderEntityToOrderDto(OrderEntity orderEntity);
    OrderEntity orderDtoToOrderEntity(OrderDto orderDto);
}
