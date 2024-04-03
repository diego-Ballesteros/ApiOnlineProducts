package com.unimagdalena.onlineProducts.persistence.repository;

import com.unimagdalena.onlineProducts.persistence.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {
    List<OrderItemEntity> findAllByOrderId(int orderId);
    List<OrderItemEntity> findAllByProductId(int productId);
    boolean existsByIdOrderItem(int id);
    OrderItemEntity findByIdOrderItem(int id);
    @Query("SELECT SUM(oi.quantity * oi.unitPrice) FROM OrderItemEntity oi WHERE oi.productId = :productId")
    Double sumTotalSalesByProductId(@Param("productId") Long productId);
    void delete(int id);
}
