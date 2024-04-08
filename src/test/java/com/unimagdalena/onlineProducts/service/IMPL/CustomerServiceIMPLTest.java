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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
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
    CustomerEntity customerEntity2;
    CustomerDto customerDto;
    CustomerDto customerDto2;

    @BeforeEach
    void setUp(){
        customerEntity = CustomerEntity.builder()
                .idCustomer(1L)
                .name("Diego Ballesteros")
                .email("diego@gmail")
                .address("concepcion 4")
                .orders(new ArrayList<>())
                .build();

        customerEntity2 = CustomerEntity.builder()
                .idCustomer(2L)
                .name("Diego Cediel")
                .email("diegoc@gmail")
                .address("concepcion 5")
                .orders(new ArrayList<>())
                .build();

        customerDto = CustomerDto.builder()
                .name("Diego Ballesteros")
                .email("diego@gmail")
                .address("concepcion 4")
                .build();

        customerDto2 = CustomerDto.builder()
                .name("Diego Cediel")
                .email("diegoc@gmail")
                .address("concepcion 5")
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
    @DisplayName("JUnit test for findAllNameStarting method")
    @Test
    void givenName_whenFindAllNameStarting_thenReturnListOfCustomerDtos() {
        // Arrange
        String name = "Diego";
        List<CustomerEntity> customerEntities = new ArrayList<>();
        customerEntities.add(customerEntity);customerEntities.add(customerEntity2);
        when(this.customerRepository.findAllByNameStartingWith(name)).thenReturn(customerEntities);
        when(this.customerMapper.customerEntityToCustomerDto(customerEntity)).thenReturn(customerDto);
        when(this.customerMapper.customerEntityToCustomerDto(customerEntity2)).thenReturn(customerDto2);

        // Create a list of expected CustomerDto
        List<CustomerDto> expectedCustomerDtos = new ArrayList<>();
        expectedCustomerDtos.add(customerDto);expectedCustomerDtos.add(customerDto2);

        List<CustomerDto> resultCustomers = this.customerServiceIMPL.findAllNameStarting(name);

        assertEquals(expectedCustomerDtos.size(), resultCustomers.size());
        assertEquals(expectedCustomerDtos, resultCustomers);
        assertEquals("Diego Ballesteros", resultCustomers.get(0).getName());
        assertEquals("Diego Cediel", resultCustomers.get(1).getName());
    }

    @Test
    void whenFindAll_thenReturnListOfCustomerDtos(){
        List<CustomerEntity> customerEntities = new ArrayList<>();
        customerEntities.add(customerEntity);customerEntities.add(customerEntity2);

        when(this.customerRepository.findAll()).thenReturn(customerEntities);
        when(this.customerMapper.customerEntityToCustomerDto(customerEntity)).thenReturn(customerDto);
        when(this.customerMapper.customerEntityToCustomerDto(customerEntity2)).thenReturn(customerDto2);

        List<CustomerDto> expectedCustomerDtos = new ArrayList<>();
        expectedCustomerDtos.add(customerDto); expectedCustomerDtos.add(customerDto2);

        List<CustomerDto> resultCustomerDtos = this.customerServiceIMPL.findAll();

        assertNotNull(resultCustomerDtos);
        assertEquals(expectedCustomerDtos.size(), resultCustomerDtos.size());
        assertEquals(expectedCustomerDtos, resultCustomerDtos);
    }

    @Test
    void givenCustomer_whenSaveCustomer_thenReturnSavedCustomer(){
        CustomerEntity customerToSave = CustomerEntity.builder()
                .idCustomer(1L)
                .name("Diego Ballesteros")
                .email("diego@gmail")
                .address("concepcion 4")
                .orders(new ArrayList<>())
                .build();
        when(this.customerRepository.save(customerToSave)).thenReturn(customerEntity);

        CustomerEntity savedCustomer = this.customerServiceIMPL.save(customerToSave);

        assertNotNull(savedCustomer);
        assertEquals(savedCustomer, customerEntity);
        assertEquals(savedCustomer.getName(), customerEntity.getName());
    }

    @Test
    void givenValidIdAndCustomerDto_whenUpdate_thenCustomerIsUpdated() {

        int id = 1;
        CustomerDto updateCustomerDto = CustomerDto.builder()
                .name("Updated Name")
                .email("updated@email.com")
                .address("Updated Address")
                .build();

        when(this.customerRepository.findById((long) id)).thenReturn(Optional.of(customerEntity));
        when(this.customerRepository.save(customerEntity)).thenReturn(customerEntity);

        CustomerEntity updatedCustomer = this.customerServiceIMPL.Update(id, updateCustomerDto);

        assertNotNull(updatedCustomer);
        assertEquals(updateCustomerDto.getName(), updatedCustomer.getName());
        assertEquals(updateCustomerDto.getEmail(), updatedCustomer.getEmail());
        assertEquals(updateCustomerDto.getAddress(), updatedCustomer.getAddress());
    }

    @Test
    void givenInvalidId_whenUpdate_thenThrowException() {

        int invalidId = 999;
        CustomerDto updateCustomerDto = CustomerDto.builder()
                .name("Updated Name")
                .email("updated@email.com")
                .address("Updated Address")
                .build();

        when(this.customerRepository.findById(Long.valueOf(invalidId))).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> this.customerServiceIMPL.Update(invalidId, updateCustomerDto),
                "Debería lanzarse una excepción cuando no se encuentra el cliente con el ID proporcionado");
    }

    @Test
    void givenValidId_whenDeleteById_thenCustomerIsDeleted() {
        int idToDelete = 1;

        this.customerServiceIMPL.deleteById(idToDelete);

        verify(this.customerRepository).deleteById((long) idToDelete);
    }

    @Test
    void givenValidId_whenExistById_thenReturnTrue() {
        int id = 1;
        when(this.customerRepository.existsByIdCustomer(id)).thenReturn(true);

        boolean result = this.customerServiceIMPL.existById(id);

        assertTrue(result);
    }

    @Test
    void givenInvalidId_whenExistById_thenReturnFalse() {
        int invalidId = 999;
        when(this.customerRepository.existsByIdCustomer(invalidId)).thenReturn(false);

        boolean result = this.customerServiceIMPL.existById(invalidId);

        assertFalse(result);
    }

}