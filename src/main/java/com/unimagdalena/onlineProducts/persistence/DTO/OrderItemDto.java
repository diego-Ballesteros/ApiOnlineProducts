package com.unimagdalena.onlineProducts.persistence.DTO;

import lombok.Data;

@Data
public class OrderItemDto {

    private Integer quantity;
    private Double unitPrice;
    private Long orderId;
    private Long productId;

}
