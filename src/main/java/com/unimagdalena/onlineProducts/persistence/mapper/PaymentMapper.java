package com.unimagdalena.onlineProducts.persistence.mapper;

import com.unimagdalena.onlineProducts.persistence.DTO.PaymentDto;
import com.unimagdalena.onlineProducts.persistence.entity.PaymentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentDto paymentEntityToPaymentDto(PaymentEntity payment);
    PaymentEntity paymentDtoToPaymentEntity(PaymentDto paymentDto);
}
