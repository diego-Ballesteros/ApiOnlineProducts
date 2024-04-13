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

        updatePaymentDto = PaymentDto.builder()
                // payment1
                .paymentTotal(600.00)
                .paymentDate(LocalDateTime.of(2023, 4, 10, 0, 0))
                .paymentMethod(PaymentEntity.MethodPay.CASH)
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
    @DisplayName("JUnit test for find payment by date between Method")
    @Test
    void findAllByPaymentDateBetween() {
        LocalDateTime startDate = LocalDateTime.of(2021, 4, 10, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 5, 15, 0, 0);

        when(this.paymentRepository.findAllByPaymentDateBetween(startDate, endDate)).thenReturn(listPaymentEntities);
        when(this.paymentMapper.paymentEntityToPaymentDto(payment1)).thenReturn(paymentDto1);
        when(this.paymentMapper.paymentEntityToPaymentDto(payment2)).thenReturn(paymentDto2);

        List<PaymentDto> resultPaymentDtos = this.paymentServiceIMPL.findAllByPaymentDateBetween(startDate, endDate);

        assertNotNull(resultPaymentDtos);
        assertEquals(listExpectedPaymentDtos.size(), resultPaymentDtos.size());
        assertEquals(listExpectedPaymentDtos, resultPaymentDtos);
        assertEquals(listExpectedPaymentDtos.get(0).getPaymentMethod(), resultPaymentDtos.get(0).getPaymentMethod());
    }

    @DisplayName("JUnit test for save payment Method")
    @Test
    void save() {
        when(this.paymentRepository.save(payment1)).thenReturn(payment1);
        PaymentEntity savedPayment = this.paymentServiceIMPL.save(payment1);
        assertNotNull(savedPayment.getIdPayment());
        assertEquals(payment1, savedPayment);
    }

    @DisplayName("JUnit test for update payment Method")
    @Test
    void update() {
        int idPayemnt = 1;
        when(this.paymentRepository.findById((long) idPayemnt)).thenReturn(Optional.ofNullable(payment1));
        when(this.paymentRepository.save(payment1)).thenReturn(payment1);

        PaymentEntity resultPaymen = this.paymentServiceIMPL.update(idPayemnt,updatePaymentDto);

        assertNotNull(resultPaymen);
        assertEquals(600.00, resultPaymen.getPaymentTotal());
        assertEquals(LocalDateTime.of(2023, 4, 10, 0, 0), resultPaymen.getPaymentDate());
    }

    @DisplayName("JUnit test for invalid update payment Method")
    @Test
    void updateInvalid() {
        int idInvalid = 999;
        when(this.paymentRepository.findById((long) idInvalid)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> this.paymentServiceIMPL.update(idInvalid, updatePaymentDto));
    }

    @DisplayName("JUnit test for delete payment Method")
    @Test
    void delete() {
        int idPayment = 1;
        this.paymentServiceIMPL.delete(idPayment);
        verify(this.paymentRepository).deleteById((long) idPayment);
    }

    @DisplayName("JUnit test for exist payment by id Method")
    @Test
    void existById() {
        int idPayment = 1;
        when(this.paymentRepository.existsById((long) idPayment)).thenReturn(true);
        boolean result = this.paymentServiceIMPL.existById(idPayment);
        assertTrue(result);
    }

    @DisplayName("JUnit test for exist payment by invalid id")
    @Test
    void existByIdInvalid() {
        int idPayment = 999;
        when(this.paymentRepository.existsById((long) idPayment)).thenReturn(false);
        boolean result = this.paymentServiceIMPL.existById(idPayment);
        assertFalse(result);
    }
}