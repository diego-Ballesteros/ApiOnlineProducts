package com.unimagdalena.onlineProducts.persistence.DTO;

import com.unimagdalena.onlineProducts.persistence.entity.OrderEntity;
import com.unimagdalena.onlineProducts.persistence.entity.OrderItemEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderDto {

    private LocalDateTime oderDate;
    private OrderEntity.Status status;
    private Long customerId;
    private Long paymentId;

    //private List<OrderItemEntity> items;
}
