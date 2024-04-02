package com.unimagdalena.onlineProducts.service;

import com.unimagdalena.onlineProducts.persistence.DTO.CustomerDto;
import com.unimagdalena.onlineProducts.persistence.entity.CustomerEntity;

import java.util.List;

public interface CustomerService {
    CustomerDto findByEmail(String email);
    List<CustomerDto> findByAddress(String address);
    List<CustomerDto> findAllNameStarting (String name);
    List<CustomerDto> findAll ();

    // CRUD ---
    CustomerEntity save(CustomerEntity clientEntity);
    void deleteById(int id);

    CustomerEntity findByid(int id);

    boolean existById(int id);
}
