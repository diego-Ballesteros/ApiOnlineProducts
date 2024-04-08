package com.unimagdalena.onlineProducts.persistence.repository;

import com.unimagdalena.onlineProducts.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    CustomerEntity findByEmail(String emial);
    CustomerEntity findByAddress(String address);
    List<CustomerEntity> findAllByNameStartingWith(String name);
    boolean existsByIdCustomer(int id);
    CustomerEntity findByIdCustomer(int id);
}
