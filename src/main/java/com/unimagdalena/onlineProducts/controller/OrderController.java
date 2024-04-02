package com.unimagdalena.onlineProducts.controller;

import com.unimagdalena.onlineProducts.persistence.DTO.OrderDto;
import com.unimagdalena.onlineProducts.persistence.entity.OrderEntity;
import com.unimagdalena.onlineProducts.persistence.entity.OrderItemEntity;
import com.unimagdalena.onlineProducts.persistence.mapper.OrderMapper;
import com.unimagdalena.onlineProducts.service.OrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }
    @GetMapping
    public ResponseEntity<List<OrderDto>> getAll(){
        return ResponseEntity.ok(this.orderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getByid(@PathVariable int id){
        return ResponseEntity.ok(this.orderService.getById(id));
    }

    @PostMapping()
    public ResponseEntity<OrderDto> addOrder (@RequestBody OrderDto orderDto){
        OrderEntity orderEntity = this.orderMapper.orderDtoToOrderEntity(orderDto);
        if(orderEntity.getIdOrder()==null || !this.orderService.existById(Math.toIntExact(orderEntity.getIdOrder()))){
            OrderEntity orderCreated = this.orderService.save(orderEntity);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{idOrder}")
                    .buildAndExpand(orderCreated.getIdOrder())
                    .toUri();
            OrderDto orderDto1 = this.orderMapper.orderEntityToOrderDto(orderCreated);
            return  ResponseEntity.created(location).body(orderDto1);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<OrderEntity> update(@PathVariable int id, @RequestBody OrderDto orderDto){
        OrderEntity order = this.orderService.update(id,orderDto);
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        if(this.orderService.existById(id)){
            this.orderService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("/date-range")
    public ResponseEntity<List<OrderDto>> findAllByOderDateBetween(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                                   @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate){
        return ResponseEntity.ok(this.orderService.findAllByOderDateBetween(startDate, endDate));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderDto>> findByCustomerId(@PathVariable int customerId){
        List<OrderDto> orderDtos = this.orderService.findByCustomerId(customerId)
                .stream().map(orderEntity -> this.orderMapper.orderEntityToOrderDto(orderEntity))
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderDtos);
    }
 
}
