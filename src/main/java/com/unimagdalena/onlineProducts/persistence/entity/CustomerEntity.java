package com.unimagdalena.onlineProducts.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Entity
@Table(name = "Customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CustomerEntity {

    @Id
    @Column(name = "id_customer", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCustomer;

    @Column(nullable = false, length = 200)
    private String name;

    @Column( unique = true)
    private String email;

    private String address;

    // Relations ------------
   @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<OrderEntity> orders;

}
