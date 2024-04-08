package com.unimagdalena.onlineProducts.persistence.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipmentDetailDto {

    private String address;
    private String carrier;
    private Long guideNumber;
    private Long orderId;
}
