package com.unimagdalena.onlineProducts.persistence.repository;

import com.unimagdalena.onlineProducts.persistence.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
    boolean existsByIdPayment(int id);
    PaymentEntity findByIdPayment(int id);
    List<PaymentEntity> findAllByPaymentDateBetween(LocalDateTime startDate, LocalDateTime endDate);

}
