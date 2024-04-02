package com.unimagdalena.onlineProducts.persistence.DTO;

import com.unimagdalena.onlineProducts.persistence.entity.OrderEntity;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class OrderDto {

    private LocalDateTime oderDate;
    private OrderEntity.Status status;
}
