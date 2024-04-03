package com.unimagdalena.onlineProducts.service;

import com.unimagdalena.onlineProducts.persistence.DTO.PaymentDto;
import com.unimagdalena.onlineProducts.persistence.entity.PaymentEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentService {
    List<PaymentDto> getAll();
    PaymentDto getById(int id);
    boolean existsByIdPayment(int id);
    List<PaymentDto> findAllByPaymentDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    PaymentEntity save(PaymentEntity paymentEntity);
    PaymentEntity update(int id, PaymentDto paymentDto);
    void delete (int id );
    boolean existById(int id);
}
