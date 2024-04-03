package com.unimagdalena.onlineProducts.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "OrderItems")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemEntity {
    @Id
    @Column(name = "id_order_item", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrderItem;

    private Integer quantity;

    @Column(name = "unit_price", nullable = false)
    private Double unitPrice;

    // FK---------------

    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "product_id")
    private Long productId;

    // Relation

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id_order",
            insertable = false, updatable = false)
    @JsonIgnore
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id_product",
            insertable = false, updatable = false)
    @JsonIgnore
    private ProductEntity product;
}
