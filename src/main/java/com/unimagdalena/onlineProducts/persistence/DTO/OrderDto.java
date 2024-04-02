package com.unimagdalena.onlineProducts.persistence.DTO;

import com.unimagdalena.onlineProducts.persistence.entity.OrderEntity;
import com.unimagdalena.onlineProducts.persistence.entity.OrderItemEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {

    private LocalDateTime oderDate;
    private OrderEntity.Status status;
    //private List<OrderItemEntity> items;
}
