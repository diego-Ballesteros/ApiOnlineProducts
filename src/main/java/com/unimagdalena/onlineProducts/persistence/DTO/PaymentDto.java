package com.unimagdalena.onlineProducts.persistence.DTO;

import com.unimagdalena.onlineProducts.persistence.entity.PaymentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDto {

    private Double paymentTotal;
    private LocalDateTime paymentDate;
    private PaymentEntity.MethodPay paymentMethod;

}
