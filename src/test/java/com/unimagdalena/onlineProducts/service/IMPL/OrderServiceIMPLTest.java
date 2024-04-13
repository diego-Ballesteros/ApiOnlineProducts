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
import java.util.Optional;

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
    OrderDto orderDto1, orderDto2, updateOrder;
    List<OrderEntity> orderEntities = new ArrayList<>();
    List<OrderDto> expectedOrdersDto = new ArrayList<>();
    @BeforeEach
    void setUp(){
         orderEntity1 = OrderEntity.builder()
                 .idOrder(1L)
                 .oderDate(LocalDateTime.of(2022, 4, 10, 0, 0))
                 .status(OrderEntity.Status.PENDING)
                 .customerId(1L)
                 .customer(CustomerEntity.builder()
                         .idCustomer(1L)
                         .name("Diego").build())
                 .payment(PaymentEntity.builder()
                         .idPayment(1L).build())
                 .paymentId(1L)
                 .orderItems(new ArrayList<>())
                 .shipmentDetails(new ArrayList<>())
                 .build();
        orderEntity2 = OrderEntity.builder()
                .idOrder(2L)
                .oderDate(LocalDateTime.of(2024, 4, 10, 0, 0))
                .status(OrderEntity.Status.PENDING)
                .customerId(1L)
                .customer(CustomerEntity.builder()
                        .idCustomer(1L)
                        .name("Diego").build())
                .payment(PaymentEntity.builder()
                        .idPayment(2L).build())
                .paymentId(2L)
                .orderItems(new ArrayList<>())
                .shipmentDetails(new ArrayList<>())
                .build();

        orderDto1 = OrderDto.builder()
                .oderDate(LocalDateTime.of(2022, 4, 10, 0, 0))
                .status(OrderEntity.Status.PENDING)
                .customerId(1L)
                .paymentId(1L)
                .build();
        orderDto2 = OrderDto.builder()
                .oderDate(LocalDateTime.of(2024, 4, 10, 0, 0))
                .status(OrderEntity.Status.PENDING)
                .customerId(1L)
                .paymentId(2L)
                .build();
        updateOrder = OrderDto.builder()
                .oderDate(LocalDateTime.of(2024, 4, 10, 0, 0))
                .status(OrderEntity.Status.DELIVERED)
                .customerId(1L)
                .paymentId(2L)
                .build();

        orderEntities.add(orderEntity1);orderEntities.add(orderEntity2);
        expectedOrdersDto.add(orderDto1); expectedOrdersDto.add(orderDto2);
    }

    @DisplayName("JUnit test for find all orders Method")
    @Test
    void givenfindAll_whenReturn() {
        when(this.oderRepository.findAll()).thenReturn(orderEntities);
        when(this.orderMapper.orderEntityToOrderDto(orderEntity1)).thenReturn(orderDto1);
        when(this.orderMapper.orderEntityToOrderDto(orderEntity2)).thenReturn(orderDto2);

        List<OrderDto> resultOrdersDto = this.orderServiceIMPL.findAll();

        assertNotNull(resultOrdersDto);
        assertEquals(expectedOrdersDto, resultOrdersDto);
        assertEquals(expectedOrdersDto.size(), resultOrdersDto.size());
    }

    @DisplayName("JUnit test for find all orders between date Method")
    @Test
    void givenDate_whenFindAllByOderDateBetween_retunrListOrderDtos() {
        LocalDateTime startDate = LocalDateTime.of(2021, 4, 10, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 5, 15, 0, 0);

        when(this.oderRepository.findAllByOderDateBetween(startDate, endDate)).thenReturn(orderEntities);
        when(this.orderMapper.orderEntityToOrderDto(orderEntity1)).thenReturn(orderDto1);
        when(this.orderMapper.orderEntityToOrderDto(orderEntity2)).thenReturn(orderDto2);

        List<OrderDto> resultOrdersDto = this.orderServiceIMPL.findAllByOderDateBetween(startDate, endDate);

        assertNotNull(resultOrdersDto);
        assertEquals(expectedOrdersDto.size(), resultOrdersDto.size());
        assertEquals(expectedOrdersDto, resultOrdersDto);
        assertEquals(expectedOrdersDto.get(0).getOderDate(), resultOrdersDto.get(0).getOderDate());

    }

    @DisplayName("JUnit test for find all orders between date Method")
    @Test
    void givenDate_whenFindAllByOderDateBetween_retunrOneOrderDtos() {
        LocalDateTime startDate = LocalDateTime.of(2021, 4, 10, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2023, 5, 15, 0, 0);

        when(this.oderRepository.findAllByOderDateBetween(startDate, endDate)).thenReturn(orderEntities);
        when(this.orderMapper.orderEntityToOrderDto(orderEntity1)).thenReturn(orderDto1);
        when(this.orderMapper.orderEntityToOrderDto(orderEntity2)).thenReturn(orderDto2);

        List<OrderDto> resultOrdersDto = this.orderServiceIMPL.findAllByOderDateBetween(startDate, endDate);

        assertNotNull(resultOrdersDto);
        assertEquals(orderDto1, resultOrdersDto.get(0));
        //assertEquals(1, resultOrdersDto.size());
        assertEquals(orderEntity1.getOderDate(), resultOrdersDto.get(0).getOderDate());
    }

    @DisplayName("JUnit test for find all orders by customer and specific status")
    @Test
    void givenCustomerAndStatus_whenFindAllByCustomerAndStatus_thenReturnListOrdersDto() {
        OrderEntity.Status status = OrderEntity.Status.PENDING;
        CustomerEntity customer = CustomerEntity.builder()
                                    .idCustomer(1L)
                                    .name("Diego").build();
        when(this.oderRepository.findAllByCustomerAndStatus(customer, status)).thenReturn(orderEntities);
        when(this.orderMapper.orderEntityToOrderDto(orderEntity1)).thenReturn(orderDto1);
        when(this.orderMapper.orderEntityToOrderDto(orderEntity2)).thenReturn(orderDto2);

        List<OrderDto> resultOrdersDto = this.orderServiceIMPL.findAllByCustomerAndStatus(customer, status);

        assertNotNull(resultOrdersDto);
        assertEquals(expectedOrdersDto, resultOrdersDto);
        assertEquals(1, resultOrdersDto.get(0).getCustomerId());
        assertEquals(1, resultOrdersDto.get(1).getCustomerId());
    }
    @DisplayName("JUnit test for find order by id")
    @Test
    void givenOrderId_whenGetById_thenReturnOrderDto() {
        int idOrder = 1;
        when(this.oderRepository.findByIdOrder(idOrder)).thenReturn(orderEntity1);
        when(this.orderMapper.orderEntityToOrderDto(orderEntity1)).thenReturn(orderDto1);
        when(this.orderMapper.orderDtoToOrderEntity(orderDto1)).thenReturn(orderEntity1);

        Optional<OrderDto> resultOrderDto = this.orderServiceIMPL.getById(idOrder);

        assertTrue(resultOrderDto.isPresent());
        assertEquals(orderDto1, resultOrderDto.get());
        assertEquals(1l, this.orderMapper.orderDtoToOrderEntity(resultOrderDto.get()).getIdOrder());
    }
    @DisplayName("JUnit test for save method")
    @Test
    void save() {
        when(this.oderRepository.save(orderEntity1)).thenReturn(orderEntity1);

        OrderEntity savedOrder = this.orderServiceIMPL.save(orderEntity1);

        assertNotNull(savedOrder);
        assertEquals(orderEntity1, savedOrder);
        assertEquals(orderEntity1.getIdOrder(), savedOrder.getIdOrder());

    }

    @DisplayName("JUnit test for update method")
    @Test
    void update() {
        int idOrder  = 1;
        when(this.oderRepository.findById((long) idOrder)).thenReturn(Optional.ofNullable(orderEntity1));
        when(this.oderRepository.save(orderEntity1)).thenReturn(orderEntity1);

        OrderEntity savedOrder = this.orderServiceIMPL.update(idOrder, updateOrder);

        assertNotNull(savedOrder);
        assertEquals(OrderEntity.Status.DELIVERED, savedOrder.getStatus());
    }

    @DisplayName("JUnit test for Find By id customer method")
    @Test
    void findByCustomerId() {
        int idCustomer= 1;
        when(this.oderRepository.findByCustomerId(idCustomer)).thenReturn(orderEntities);
        when(this.orderMapper.orderEntityToOrderDto(orderEntity1)).thenReturn(orderDto1);
        when(this.orderMapper.orderEntityToOrderDto(orderEntity2)).thenReturn(orderDto2);


        List<OrderDto> resultOrdersDto = this.orderServiceIMPL.findByCustomerId(idCustomer);

        assertNotNull(resultOrdersDto);
        assertEquals(expectedOrdersDto.size(), resultOrdersDto.size());
        assertEquals(1L, resultOrdersDto.get(0).getCustomerId());
        assertEquals(1L, resultOrdersDto.get(1).getCustomerId());
    }
    @DisplayName("JUnit test for delete method")
    @Test
    void delete() {
        int id = 1;
        this.orderServiceIMPL.delete(id);
        verify(this.oderRepository).deleteById((long) id);
    }

    @DisplayName("JUnit test for exist By ID method")
    @Test
    void existById() {
        int id = 1;
        when(this.oderRepository.existsById((long) id)).thenReturn(true);
        boolean result = this.orderServiceIMPL.existById(id);
        assertTrue(result);
    }
    @DisplayName("JUnit test for Find By invalid id customer method")
    @Test
    void notExistById() {
        int id = 999;
        when(this.oderRepository.existsById((long) id)).thenReturn(false);
        boolean result = this.orderServiceIMPL.existById(id);
        assertFalse(result);
    }
}