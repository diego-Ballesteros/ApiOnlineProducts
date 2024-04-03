package com.unimagdalena.onlineProducts.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer", nullable = false)
    private Long idCustomer;

    @Column(nullable = false, length = 200)
    private String name;

    @Column( unique = true)
    private String email;

    private String address;

    // Relations ------------
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<OrderEntity> orders;
}
