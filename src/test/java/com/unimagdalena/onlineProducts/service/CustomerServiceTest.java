package com.unimagdalena.onlineProducts.service;

import com.unimagdalena.onlineProducts.persistence.DTO.CustomerDto;
import com.unimagdalena.onlineProducts.persistence.entity.CustomerEntity;
import com.unimagdalena.onlineProducts.persistence.mapper.CustomerMapper;
import com.unimagdalena.onlineProducts.persistence.repository.CustomerRepository;
import com.unimagdalena.onlineProducts.service.IMPL.CustomerServiceIMPL;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerMapper customerMapper;
    @InjectMocks
    private CustomerServiceIMPL customerServiceIMPL;

    CustomerEntity customerEntity;
    CustomerDto customerDto;

    @BeforeEach
    public void setup(){
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
    public void givenCustomerEmail_whenFindByEmail_thenReturnListCustomerDto() {
        String email = "diego@gmail";

        when(this.customerRepository.findByEmail(email)).thenReturn(customerEntity);
        when(this.customerMapper.customerEntityToCustomerDto(customerEntity)).thenReturn(customerDto);

        Optional<CustomerDto> savedCustomer = this.customerServiceIMPL.findByEmail(email);

        assertTrue(savedCustomer.isPresent());
        assertEquals(customerDto, savedCustomer.get());
        assertEquals(savedCustomer.get().getEmail(), customerEntity.getEmail());
    }

}
