package com.unimagdalena.onlineProducts.persistence.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemDto {

    private Integer quantity;
    private Double unitPrice;
    private Long orderId;
    private Long productId;

}
