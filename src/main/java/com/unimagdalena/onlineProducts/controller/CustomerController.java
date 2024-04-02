package com.unimagdalena.onlineProducts.controller;

import com.unimagdalena.onlineProducts.persistence.DTO.CustomerDto;
import com.unimagdalena.onlineProducts.persistence.entity.CustomerEntity;
import com.unimagdalena.onlineProducts.persistence.mapper.CustomerMapper;
import com.unimagdalena.onlineProducts.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/customers")
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
    @GetMapping("/email")
    public ResponseEntity<CustomerDto> getByEmail(@RequestParam String email){
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
    public ResponseEntity<CustomerDto> addClient(CustomerEntity clientEntity){
        if(clientEntity.getIdCustomer()==null || !this.customerService.existById(Math.toIntExact(clientEntity.getIdCustomer()))){
            CustomerEntity clientCreated = this.customerService.save(clientEntity);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{idCustomer}")
                    .buildAndExpand(clientCreated.getIdCustomer())
                    .toUri();
            CustomerDto clientDto = this.customerMapper.customerEntityToCustomerDto(clientCreated);
            return  ResponseEntity.created(location).body(clientDto);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<CustomerDto> update(@PathVariable int id){
        if(this.customerService.existById(id)){
            CustomerEntity clientFind = this.customerService.findById(id);
            CustomerEntity clientCreated = this.customerService.save(clientFind);
            CustomerDto clientDto = this.customerMapper.customerEntityToCustomerDto(clientCreated);
            return  ResponseEntity.ok(clientDto);
        }
        return ResponseEntity.badRequest().build();
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
