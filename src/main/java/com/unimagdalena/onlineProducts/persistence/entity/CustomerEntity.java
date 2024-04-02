package com.unimagdalena.onlineProducts.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
