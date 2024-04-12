package com.unimagdalena.onlineProducts.service.IMPL;

import com.unimagdalena.onlineProducts.persistence.DTO.OrderDto;
import com.unimagdalena.onlineProducts.persistence.entity.CustomerEntity;
import com.unimagdalena.onlineProducts.persistence.entity.OrderEntity;
import com.unimagdalena.onlineProducts.persistence.entity.PaymentEntity;
import com.unimagdalena.onlineProducts.persistence.mapper.OrderMapper;
import com.unimagdalena.onlineProducts.persistence.repository.OderRepository;
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

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class OrderServiceIMPLTest {
    @InjectMocks
    private OrderServiceIMPL orderServiceIMPL;
    @Mock
    private OrderMapper orderMapper;
    @Mock
    private OderRepository oderRepository;

    OrderEntity orderEntity1, orderEntity2;
    OrderDto orderDto1, orderDto2;
    List<OrderEntity> orderEntities = new ArrayList<>();
    List<OrderDto> expectedOrdersDto = new ArrayList<>();
    @BeforeEach
    void setUp(){
         orderEntity1 = OrderEntity.builder()
                 .idOrder(1L)
                 .oderDate(LocalDateTime.now())
                 .status(OrderEntity.Status.PENDING)
                 .customerId(1L)
                 .customer(CustomerEntity.builder()
                         .idCustomer(1L).build())
                 .payment(PaymentEntity.builder()
                         .idPayment(1L).build())
                 .paymentId(1L)
                 .orderItems(new ArrayList<>())
                 .shipmentDetails(new ArrayList<>())
                 .build();
        orderEntity2 = OrderEntity.builder()
                .idOrder(2L)
                .oderDate(LocalDateTime.now())
                .status(OrderEntity.Status.SENT)
                .customerId(2L)
                .customer(CustomerEntity.builder()
                        .idCustomer(2L).build())
                .payment(PaymentEntity.builder()
                        .idPayment(2L).build())
                .paymentId(2L)
                .orderItems(new ArrayList<>())
                .shipmentDetails(new ArrayList<>())
                .build();

        orderDto1 = OrderDto.builder()
                .oderDate(LocalDateTime.now())
                .status(OrderEntity.Status.PENDING)
                .customerId(1L)
                .paymentId(1L)
                .build();
        orderDto2 = OrderDto.builder()
                .oderDate(LocalDateTime.now())
                .status(OrderEntity.Status.SENT)
                .customerId(2L)
                .paymentId(2L)
                .build();

        orderEntities.add(orderEntity1);orderEntities.add(orderEntity2);
        expectedOrdersDto.add(orderDto1); expectedOrdersDto.add(orderDto2);
    }

    @DisplayName("JUnit test for find all products in stock Method")
    @Test
    void findAll() {
        when(this.oderRepository.findAll()).thenReturn(orderEntities);
        when(this.orderMapper.orderEntityToOrderDto(orderEntity1)).thenReturn(orderDto1);
        when(this.orderMapper.orderEntityToOrderDto(orderEntity2)).thenReturn(orderDto2);

        List<OrderDto> resultOrdersDto = this.orderServiceIMPL.findAll();

        assertNotNull(resultOrdersDto);
        assertEquals(expectedOrdersDto, resultOrdersDto);
        assertEquals(expectedOrdersDto.size(), resultOrdersDto.size());
    }

    @Test
    void findAllByOderDateBetween() {
    }

    @Test
    void findAllByCustomerAndStatus() {
    }
    @Test
    void getById() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void findByCustomerId() {
    }

    @Test
    void delete() {
    }

    @Test
    void existById() {
    }
}