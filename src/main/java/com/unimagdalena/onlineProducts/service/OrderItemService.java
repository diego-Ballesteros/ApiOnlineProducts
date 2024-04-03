package com.unimagdalena.onlineProducts.service;

import com.unimagdalena.onlineProducts.persistence.DTO.OrderItemDto;
import com.unimagdalena.onlineProducts.persistence.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemService {
    List<OrderItemDto> getAll();
    List<OrderItemDto> findAllByOrderId(int orderId);
    List<OrderItemDto> findAllByProductId(int productId);
    boolean existsByIdOrderItem(int id);
    OrderItemDto findByIdOrderItem(int id);
    Double sumTotalSalesByProductId(@Param("productId") Long productId);
    OrderItemEntity save(OrderItemEntity orderItemEntity);
    OrderItemEntity update(int id, OrderItemDto orderItemDto);
    void delete(int id);

}
