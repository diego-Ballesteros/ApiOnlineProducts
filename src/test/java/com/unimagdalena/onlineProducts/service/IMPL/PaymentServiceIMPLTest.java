package com.unimagdalena.onlineProducts.service.IMPL;

import com.unimagdalena.onlineProducts.persistence.DTO.PaymentDto;
import com.unimagdalena.onlineProducts.persistence.entity.PaymentEntity;
import com.unimagdalena.onlineProducts.persistence.mapper.PaymentMapper;
import com.unimagdalena.onlineProducts.persistence.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentServiceIMPLTest {
    @Mock
    PaymentRepository paymentRepository;
    @Mock
    PaymentMapper paymentMapper;
    @InjectMocks
    PaymentServiceIMPL paymentServiceIMPL;

    PaymentEntity payment1, payment2;
    PaymentDto paymentDto1, paymentDto2, updatePaymentDto;
    List<PaymentEntity> listPaymentEntities = new ArrayList<>();
    List<PaymentDto> listExpectedPaymentDtos = new ArrayList<>();
    @BeforeEach
    void setUp(){

        payment1 = PaymentEntity.builder()
                .idPayment(1l)
                .paymentTotal(800.00)
                .paymentDate(LocalDateTime.of(2022, 4, 10, 0, 0))
                .paymentMethod(PaymentEntity.MethodPay.CASH)
                .build();

        payment2 = PaymentEntity.builder()
                .idPayment(2l)
                .paymentTotal(500.00)
                .paymentDate(LocalDateTime.of(2024, 4, 10, 0, 0))
                .paymentMethod(PaymentEntity.MethodPay.PSE)
                .build();
        paymentDto1 = PaymentDto.builder()
                .paymentTotal(800.00)
                .paymentDate(LocalDateTime.of(2022, 4, 10, 0, 0))
                .paymentMethod(PaymentEntity.MethodPay.CASH)
                .build();
        paymentDto2 = PaymentDto.builder()
                .paymentTotal(500.00)
                .paymentDate(LocalDateTime.of(2024, 4, 10, 0, 0))
                .paymentMethod(PaymentEntity.MethodPay.PSE)
                .build();

        listPaymentEntities.add(payment1); listPaymentEntities.add(payment2);
        listExpectedPaymentDtos.add(paymentDto1);
        listExpectedPaymentDtos.add(paymentDto2);

    }
    @DisplayName("JUnit test for find all payments Method")
    @Test
    void getAll() {

        when(this.paymentRepository.findAll()).thenReturn(listPaymentEntities);
        when(this.paymentMapper.paymentEntityToPaymentDto(payment1)).thenReturn(paymentDto1);
        when(this.paymentMapper.paymentEntityToPaymentDto(payment2)).thenReturn(paymentDto2);

        List<PaymentDto> resultPaymentsDto = this.paymentServiceIMPL.getAll();

        assertNotNull(resultPaymentsDto);
        assertEquals(listExpectedPaymentDtos, resultPaymentsDto);
        assertEquals(listExpectedPaymentDtos.get(0).getPaymentMethod(), resultPaymentsDto.get(0).getPaymentMethod());
    }
    @DisplayName("JUnit test for get by id payment Method")
    @Test
    void getById() {
        int idPayment = 1;
        when(this.paymentRepository.findByIdPayment(idPayment)).thenReturn(payment1);
        when(this.paymentMapper.paymentEntityToPaymentDto(payment1)).thenReturn(paymentDto1);

        Optional<PaymentDto> resultPaymentDto = this.paymentServiceIMPL.getById(idPayment);

        assertTrue(resultPaymentDto.isPresent());
        assertEquals(paymentDto1, resultPaymentDto.get());
    }
    @DisplayName("JUnit test for find all payments Method")
    @Test
    void findAllByPaymentDateBetween() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void existById() {
    }
}