package com.unimagdalena.onlineProducts.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    @Id
    @Column(name = "id_order", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;

    @Column(nullable = false)
    private LocalDateTime oderDate;

    @Column(nullable = false)
    private Status status;

    // FK ----------------------

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "payment_id")
    private Long paymentId;
    // Relations ---------------------

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id_client"
            , insertable = false, updatable = false)
    @JsonIgnore
    private CustomerEntity client;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderItemEntity> orderItems;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<ShipmentDetailEntity> shipmentDetails;

    @OneToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id_payment", insertable = false, updatable = false)
    private PaymentEntity payment;


    public enum Status {
        PENDING, SENT, DELIVERED
    }

}
