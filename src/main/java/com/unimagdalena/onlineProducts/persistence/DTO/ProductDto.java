package com.unimagdalena.onlineProducts.persistence.DTO;

import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private Double price;
    private Integer stock;
}
