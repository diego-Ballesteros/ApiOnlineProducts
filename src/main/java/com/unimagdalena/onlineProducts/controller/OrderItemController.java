package com.unimagdalena.onlineProducts.controller;

import com.unimagdalena.onlineProducts.persistence.DTO.OrderItemDto;
import com.unimagdalena.onlineProducts.persistence.entity.OrderItemEntity;
import com.unimagdalena.onlineProducts.persistence.mapper.OrderItemMapper;
import com.unimagdalena.onlineProducts.service.OrderItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/order-items")
public class OrderItemController {
    private final OrderItemService orderItemService;
    private final OrderItemMapper orderItemMapper;

    public OrderItemController(OrderItemService orderItemService, OrderItemMapper orderItemMapper) {
        this.orderItemService = orderItemService;
        this.orderItemMapper = orderItemMapper;
    }

    @GetMapping
    public ResponseEntity<List<OrderItemDto>> getAll(){
        return ResponseEntity.ok(this.orderItemService.getAll());
    }
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderItemDto>> findAllByOrderId(@PathVariable int orderId){
        return ResponseEntity.ok(this.orderItemService.findAllByOrderId(orderId));
    }
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<OrderItemDto>> findAllByProductId(@PathVariable int productId){
        return ResponseEntity.ok(this.orderItemService.findAllByProductId(productId));
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDto> getByid(@PathVariable int id){
        return ResponseEntity.ok(this.orderItemService.findByIdOrderItem(id));
    }
    @PostMapping()
    public ResponseEntity<OrderItemDto> addOrderItem (@RequestBody OrderItemDto orderItemDto){
        OrderItemEntity orderItemEntity = this.orderItemMapper.orderItemDtoToOrderItemEntity(orderItemDto);
        if(orderItemEntity.getIdOrderItem()==null || !this.orderItemService.existsByIdOrderItem(Math.toIntExact(orderItemEntity.getIdOrderItem()))){
            OrderItemEntity orderItemCreated = this.orderItemService.save(orderItemEntity);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{idOrder}")
                    .buildAndExpand(orderItemCreated.getIdOrderItem())
                    .toUri();
            OrderItemDto orderItemDto1 = this.orderItemMapper.orderItemEntityToOrderItemDto(orderItemCreated);
            return  ResponseEntity.created(location).body(orderItemDto1);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<OrderItemEntity> update(@PathVariable int id, @RequestBody OrderItemDto orderItemDto){
        OrderItemEntity orderItem = this.orderItemService.update(id,orderItemDto);
        return ResponseEntity.ok(orderItem);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        if(this.orderItemService.existsByIdOrderItem(id)){
            this.orderItemService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
