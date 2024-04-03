package com.unimagdalena.onlineProducts.persistence.DTO;

import lombok.Data;

@Data
public class ShipmentDetailDto {

    private String address;
    private String carrier;
    private Long guideNumber;
    private Long orderId;
}
