package com.unimagdalena.onlineProducts.service.IMPL;

import com.unimagdalena.onlineProducts.persistence.DTO.CustomerDto;
import com.unimagdalena.onlineProducts.persistence.entity.CustomerEntity;
import com.unimagdalena.onlineProducts.persistence.entity.OrderEntity;
import com.unimagdalena.onlineProducts.persistence.entity.ProductEntity;
import com.unimagdalena.onlineProducts.persistence.mapper.CustomerMapper;
import com.unimagdalena.onlineProducts.persistence.repository.CustomerRepository;
import com.unimagdalena.onlineProducts.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class CustomerServiceIMPL implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceIMPL(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDto findByEmail(String email) {
        CustomerEntity customerEntity = this.customerRepository.findByEmail(email);
        CustomerDto customerDto = this.customerMapper.customerEntityToCustomerDto(customerEntity);
        return customerDto;
    }

    @Override
    public List<CustomerDto> findByAddress(String address) {
        List<CustomerDto> customerDtos = this.customerRepository.findAllByAddress(address)
                .stream().map(customer -> this.customerMapper.customerEntityToCustomerDto(customer) )
                .collect(Collectors.toList());
        return customerDtos;
    }

    @Override
    public List<CustomerDto> findAllNameStarting(String name) {
        List<CustomerDto> customerDtos = this.customerRepository.findAllByNameStartingWith(name)
                .stream().map(customer -> this.customerMapper.customerEntityToCustomerDto(customer) )
                .collect(Collectors.toList());
        return customerDtos;
    }

    @Override
    public List<CustomerDto> findAll() {
        List<CustomerDto> customerDtos = this.customerRepository.findAll()
                .stream().map(customer -> this.customerMapper.customerEntityToCustomerDto(customer) )
                .collect(Collectors.toList());
        return customerDtos;
    }

    @Override
    public CustomerEntity save(CustomerEntity clientEntity) {
        return this.customerRepository.save(clientEntity);
    }

    @Override
    public CustomerEntity Update(int id, CustomerDto customerDto) {
        Optional<CustomerEntity> optionalCustomer = this.customerRepository.findById(Long.valueOf(id));
        if(optionalCustomer.isPresent()){
            CustomerEntity existCustomer = optionalCustomer.get();
            BeanUtils.copyProperties(customerDto, existCustomer);
            return this.customerRepository.save(existCustomer);
        }else{
            throw new RuntimeException("Customer Not found whit that Id");
        }
    }

    @Override
    public void deleteById(int id) {
        this.customerRepository.deleteById((long) id);
    }

    @Override
    public CustomerEntity findById(int id) {
        return this.customerRepository.findByIdCustomer(id);
    }

    @Override
    public boolean existById(int id) {
        return this.customerRepository.existsByIdCustomer((int) id);
    }
}
