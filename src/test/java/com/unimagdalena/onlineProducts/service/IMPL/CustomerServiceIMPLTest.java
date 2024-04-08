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
    @Test
    void givenIdCustomer_whenFindByIdCustomer_thenReturnOptionalCudtomerDto(){
        Long idCustomer = 1L;
        when(this.customerRepository.findByIdCustomer(Math.toIntExact(idCustomer))).thenReturn(customerEntity);
        when(this.customerMapper.customerEntityToCustomerDto(customerEntity)).thenReturn(customerDto);
        when(this.customerMapper.customerDtoToCustomerEntity(customerDto)).thenReturn(customerEntity);

        Optional<CustomerDto> resultCustomer = this.customerServiceIMPL.findById(Math.toIntExact(idCustomer));

        assertNotNull(resultCustomer);
        assertTrue(resultCustomer.isPresent());
        assertEquals(customerDto, resultCustomer.get());
        assertEquals(idCustomer, this.customerMapper.customerDtoToCustomerEntity(resultCustomer.get()).getIdCustomer());
    }

    @DisplayName("JUnit test for findByEmail Method")
    @Test
    void givenCustomerEmail_whenFindByEmail_thenReturnOptionalCustomerDto() {

        String email = "diego@gmail";
        when(this.customerRepository.findByEmail(email)).thenReturn(customerEntity);
        when(this.customerMapper.customerEntityToCustomerDto(customerEntity)).thenReturn(customerDto);

        Optional<CustomerDto> result = this.customerServiceIMPL.findByEmail(email);

        assertNotNull(result, "El resultado no debería ser nulo");
        assertTrue(result.isPresent(), "El resultado debería estar presente");
        assertEquals(customerDto, result.get(), "El resultado debería coincidir con el customerDto esperado");
        assertEquals(email, result.get().getEmail(), "El email en el resultado debería coincidir con el email esperado");
    }

    @DisplayName("JUnit test for findByAddress method")
    @Test
    void givenCustomerAddress_whenFindByAddress_thenReturnOptionalCustomerDto(){
        String address = "concepcion 4";
        when(this.customerRepository.findByAddress(address)).thenReturn(customerEntity);
        when(this.customerMapper.customerEntityToCustomerDto(customerEntity)).thenReturn(customerDto);

        Optional<CustomerDto> resultCustomer = this.customerServiceIMPL.findByAddress(address);

        assertNotNull(resultCustomer);
        assertTrue(resultCustomer.isPresent());
        assertEquals(customerDto, resultCustomer.get());
        assertEquals(address, resultCustomer.get().getAddress());
    }

}