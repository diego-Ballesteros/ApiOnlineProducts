package com.unimagdalena.onlineProducts.service;

import com.unimagdalena.onlineProducts.persistence.DTO.OrderDto;
import com.unimagdalena.onlineProducts.persistence.entity.CustomerEntity;
import com.unimagdalena.onlineProducts.persistence.entity.OrderEntity;
import com.unimagdalena.onlineProducts.persistence.entity.ProductEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<OrderDto> findAllByOderDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<OrderDto> findAllByCustomerAndStatus(CustomerEntity customer, OrderEntity.Status status);
    List<OrderDto> findAll();

    OrderDto getById(int id);
    OrderEntity save (OrderEntity orderEntity);

    OrderEntity update(int id, OrderDto orderDto);

    void delete (int id );

}
