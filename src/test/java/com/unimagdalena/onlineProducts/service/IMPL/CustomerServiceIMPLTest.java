package com.unimagdalena.onlineProducts.service.IMPL;

import com.unimagdalena.onlineProducts.persistence.DTO.CustomerDto;
import com.unimagdalena.onlineProducts.persistence.entity.CustomerEntity;
import com.unimagdalena.onlineProducts.persistence.mapper.CustomerMapper;
import com.unimagdalena.onlineProducts.persistence.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceIMPLTest {

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerMapper customerMapper;
    @InjectMocks
    private CustomerServiceIMPL customerServiceIMPL;

    CustomerEntity customerEntity;
    CustomerDto customerDto;

    @BeforeEach
    void setUp(){
        customerEntity = CustomerEntity.builder()
                .idCustomer(1L)
                .name("Diego")
                .email("diego@gmail")
                .address("concepcion 4")
                .orders(new ArrayList<>())
                .build();
        customerDto = CustomerDto.builder()
                .name("Diego")
                .email("diego@gmail")
                .address("concepcion 4")
                .build();
    }

    @DisplayName("JUnit test for findByEmail Method")
    @Test
    void givenCustomerEmail_whenFindByEmail_thenReturnOptionalCustomerDto() {
       // Arrange
        String email = "diego@gmail";
        when(customerRepository.findByEmail(email)).thenReturn(customerEntity);
        when(customerMapper.customerEntityToCustomerDto(customerEntity)).thenReturn(customerDto);
        // Act
        Optional<CustomerDto> result = customerServiceIMPL.findByEmail(email);
        // Assert
        assertEquals(Optional.ofNullable(customerDto), result);
    }

}