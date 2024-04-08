package com.unimagdalena.onlineProducts.service;

import com.unimagdalena.onlineProducts.persistence.DTO.CustomerDto;
import com.unimagdalena.onlineProducts.persistence.entity.CustomerEntity;
import com.unimagdalena.onlineProducts.persistence.entity.OrderEntity;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Optional<CustomerDto> findByEmail(String email);
    Optional<CustomerDto> findByAddress(String address);
    List<CustomerDto> findAllNameStarting (String name);
    List<CustomerDto> findAll ();

    // CRUD ---
    CustomerEntity save(CustomerEntity clientEntity);
    CustomerEntity Update(int id, CustomerDto customerDto);
    void deleteById(int id);
    Optional<CustomerDto> findById(int id);
    boolean existById(int id);
}
