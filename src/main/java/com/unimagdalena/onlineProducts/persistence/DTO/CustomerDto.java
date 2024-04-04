package com.unimagdalena.onlineProducts.persistence.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {
    private String name;
    private String email;
    private String address;
}
