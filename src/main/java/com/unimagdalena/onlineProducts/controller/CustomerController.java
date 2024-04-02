package com.unimagdalena.onlineProducts.controller;

import com.unimagdalena.onlineProducts.persistence.DTO.CustomerDto;
import com.unimagdalena.onlineProducts.persistence.DTO.ProductDto;
import com.unimagdalena.onlineProducts.persistence.entity.CustomerEntity;
import com.unimagdalena.onlineProducts.persistence.mapper.CustomerMapper;
import com.unimagdalena.onlineProducts.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    public CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAll(){
        return ResponseEntity.ok(this.customerService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerEntity> getById(@PathVariable int id){
        return ResponseEntity.ok(this.customerService.findById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<CustomerDto> getByEmail(@PathVariable String email){
        return ResponseEntity.ok(this.customerService.findByEmail(email));
    }
    @GetMapping("/address")
    public ResponseEntity<List<CustomerDto>> findAllByAddress(@RequestParam String address){
        return ResponseEntity.ok(this.customerService.findByAddress(address));
    }

    @GetMapping("/name-starting")
    public ResponseEntity<List<CustomerDto>> findAllByNameStartingWith(@RequestParam String name){
        return ResponseEntity.ok(this.customerService.findAllNameStarting(name));
    }

    @PostMapping()
    public ResponseEntity<CustomerDto> addClient(@RequestBody  CustomerDto customerDto){
        CustomerEntity customerEntity = this.customerMapper.customerDtoToCustomerEntity(customerDto);
        if(customerEntity.getIdCustomer()==null || !this.customerService.existById(Math.toIntExact(customerEntity.getIdCustomer()))){
            CustomerEntity customerCreated = this.customerService.save(customerEntity);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{idCustomer}")
                    .buildAndExpand(customerCreated.getIdCustomer())
                    .toUri();
            CustomerDto clientDto = this.customerMapper.customerEntityToCustomerDto(customerCreated);
            return  ResponseEntity.created(location).body(clientDto);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<CustomerEntity> update(@PathVariable int id, @RequestBody CustomerDto customerDto){
        CustomerEntity customer = this.customerService.Update(id, customerDto);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        if(this.customerService.existById(id)){
            this.customerService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
