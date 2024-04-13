package com.unimagdalena.onlineProducts.controller;

import com.unimagdalena.onlineProducts.persistence.DTO.PaymentDto;
import com.unimagdalena.onlineProducts.persistence.entity.PaymentEntity;
import com.unimagdalena.onlineProducts.persistence.mapper.PaymentMapper;
import com.unimagdalena.onlineProducts.service.PaymentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/payment")
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;

    public PaymentController(PaymentService paymentService, PaymentMapper paymentMapper) {
        this.paymentService = paymentService;
        this.paymentMapper = paymentMapper;
    }
    @GetMapping
    public ResponseEntity<List<PaymentDto>> getAll(){
        return ResponseEntity.ok(this.paymentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PaymentDto>> getById(@PathVariable int id){
        Optional<PaymentDto> paymentDto = this.paymentService.getById(id);
        if(paymentDto.isPresent()){
            return ResponseEntity.ok(paymentDto);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<PaymentDto>> findAllByPaymentDateBetween(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate){
        return ResponseEntity.ok(this.paymentService.findAllByPaymentDateBetween(startDate, endDate));
    }

    @PostMapping()
    public ResponseEntity<PaymentDto> addPayment(@RequestBody PaymentDto paymentDto){
        PaymentEntity paymentEntity = this.paymentMapper.paymentDtoToPaymentEntity(paymentDto);
        if(paymentEntity.getIdPayment()==null || !this.paymentService.existById(Math.toIntExact(paymentEntity.getIdPayment()))){
            PaymentEntity paymentCreated = this.paymentService.save(paymentEntity);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{idPayment}")
                    .buildAndExpand(paymentCreated.getIdPayment())
                    .toUri();
            PaymentDto paymentDto1 = this.paymentMapper.paymentEntityToPaymentDto(paymentCreated);
            return ResponseEntity.created(location).body(paymentDto1);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<PaymentEntity> update(@PathVariable int id, @RequestBody PaymentDto paymentDto){
        PaymentEntity payment = this.paymentService.update(id,paymentDto);
        return ResponseEntity.ok(payment);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable int id){
        if(this.paymentService.existsByIdPayment(id)){
            this.paymentService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }


}
