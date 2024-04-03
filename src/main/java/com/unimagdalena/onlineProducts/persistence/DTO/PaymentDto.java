package com.unimagdalena.onlineProducts.persistence.DTO;

import com.unimagdalena.onlineProducts.persistence.entity.PaymentEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentDto {

    private Double paymentTotal;
    private LocalDateTime paymentDate;
    private PaymentEntity.MethodPay paymentMethod;

}
