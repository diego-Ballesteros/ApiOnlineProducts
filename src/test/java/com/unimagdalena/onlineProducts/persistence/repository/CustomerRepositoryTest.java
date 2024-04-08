/*package com.unimagdalena.onlineProducts.repository;

//import com.unimagdalena.onlineProducts.AbstractIntegrationDBTest;
import com.unimagdalena.onlineProducts.persistence.entity.CustomerEntity;
import com.unimagdalena.onlineProducts.persistence.repository.CustomerRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

public class CustomerRepositoryTest extends AbstractIntegrationDBTest{
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerRepositoryTest(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @BeforeEach
    void setUp() {
        this.customerRepository.deleteAll();
    }

    @DisplayName("Test para operaciones de guardado")
    @Test
    public void givenCustomerObject_whenSavingCustomer_thenReturnCustomerObject(){
        // given
        CustomerEntity customer = CustomerEntity.builder()
                .name("Diego")
                .email("diego@gmail")
                .address("concepcion 4")
                .build();

        //when
        CustomerEntity savedCustomer = this.customerRepository.save(customer);

        //then
        assertThat(savedCustomer).isNotNull();
        assertThat(savedCustomer.getIdCustomer()).isGreaterThan(0);
        assertThat(savedCustomer.getName()).isEqualTo("Diego");
    }

}*/
