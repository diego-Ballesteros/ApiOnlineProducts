package com.unimagdalena.onlineProducts.persistence.repository;

import com.unimagdalena.onlineProducts.persistence.DTO.OrderDto;
import com.unimagdalena.onlineProducts.persistence.entity.CustomerEntity;
import com.unimagdalena.onlineProducts.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findAllByOderDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<OrderEntity> findAllByCustomerAndStatus(CustomerEntity customer, OrderEntity.Status status);

    List<OrderEntity> findByCustomerId(int idCostumer);
    OrderEntity findByIdOrder(int id);

}
