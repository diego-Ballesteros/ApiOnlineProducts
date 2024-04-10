package com.unimagdalena.onlineProducts.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEntity {
    @Id
    @Column(name = "id_product", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer stock;

    // Relations --------------

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<OrderItemEntity> orderItems;

}
