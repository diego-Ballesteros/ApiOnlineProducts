package com.unimagdalena.onlineProducts.persistence.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {
    private String name;
    private Double price;
    private Integer stock;
}
