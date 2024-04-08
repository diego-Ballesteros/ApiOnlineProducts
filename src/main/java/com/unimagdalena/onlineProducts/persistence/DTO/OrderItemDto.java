package com.unimagdalena.onlineProducts.persistence.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDto {

    private Integer quantity;
    private Double unitPrice;
    private Long orderId;
    private Long productId;

}
