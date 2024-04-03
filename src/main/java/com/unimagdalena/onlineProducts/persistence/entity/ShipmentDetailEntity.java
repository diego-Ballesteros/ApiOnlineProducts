package com.unimagdalena.onlineProducts.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ShipmentDetails")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipmentDetailEntity {
    @Id
    @Column(name = "id_detail", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetail;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String carrier;
    @Column(name = "guide_number", nullable = false)
    private Long guideNumber;
    // FK ------------------------

    @Column(name = "order_id")
    private Long orderId;

    // Relations -----------------
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id_order"
            , insertable = false, updatable = false )
    @JsonIgnore
    private OrderEntity order;
}
