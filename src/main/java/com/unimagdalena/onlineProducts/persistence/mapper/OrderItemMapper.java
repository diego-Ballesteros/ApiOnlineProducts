package com.unimagdalena.onlineProducts.persistence.mapper;

import com.unimagdalena.onlineProducts.persistence.DTO.OrderItemDto;
import com.unimagdalena.onlineProducts.persistence.entity.OrderItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItemDto orderItemEntityToOrderItemDto(OrderItemEntity orderItemEntity);
    OrderItemEntity orderItemDtoToOrderItemEntity(OrderItemDto orderItemDto);
}
