package com.unimagdalena.onlineProducts.controller;

import com.unimagdalena.onlineProducts.persistence.DTO.ShipmentDetailDto;
import com.unimagdalena.onlineProducts.persistence.entity.ShipmentDetailEntity;
import com.unimagdalena.onlineProducts.persistence.mapper.ShipmentDetailMapper;
import com.unimagdalena.onlineProducts.service.ShipmentDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/shipping")
public class ShipmentDetailController {
    private final ShipmentDetailService shipmentDetailService;
    private final ShipmentDetailMapper shipmentDetailMapper;

    public ShipmentDetailController(ShipmentDetailService shipmentDetailService, ShipmentDetailMapper shipmentDetailMapper) {
        this.shipmentDetailService = shipmentDetailService;
        this.shipmentDetailMapper = shipmentDetailMapper;
    }
    @GetMapping
    public ResponseEntity<List<ShipmentDetailDto>> getAll(){
        return ResponseEntity.ok(this.shipmentDetailService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ShipmentDetailDto>> getByid(@PathVariable int id){
        Optional<ShipmentDetailDto> shipmentDetailDto = this.shipmentDetailService.findByIdDetail(id);
        if (shipmentDetailDto.isPresent()){
            return ResponseEntity.ok(shipmentDetailDto);
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("/carrier")
    public ResponseEntity<List<ShipmentDetailDto>> findAllByCarrier(@RequestParam String carrier){
        return ResponseEntity.ok(this.shipmentDetailService.findAllByCarrier(carrier));
    }
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<ShipmentDetailDto>> findAllByOrderId(@PathVariable int orderId){
        return ResponseEntity.ok(this.shipmentDetailService.findAllByOrderId(orderId));
    }
    @PostMapping()
    public ResponseEntity<ShipmentDetailDto> addShipment (@RequestBody ShipmentDetailDto shipmentDto){
        ShipmentDetailEntity shipmentDetailEntity = this.shipmentDetailMapper.shipmentDetailDtoToShipmentDetailEntity(shipmentDto);
        if(shipmentDetailEntity.getIdDetail()==null || !this.shipmentDetailService.existsByIdDetail(Math.toIntExact(shipmentDetailEntity.getIdDetail()))){
            ShipmentDetailEntity shipmentCreated = this.shipmentDetailService.save(shipmentDetailEntity);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{idOrder}")
                    .buildAndExpand(shipmentCreated.getIdDetail())
                    .toUri();
            ShipmentDetailDto shipmentDto1 = this.shipmentDetailMapper.ShipmentDetailEntityToShipmentDetailDto(shipmentCreated);
            return  ResponseEntity.created(location).body(shipmentDto1);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<ShipmentDetailEntity> update(@PathVariable int id, @RequestBody ShipmentDetailDto shipmentDetailDto){
        ShipmentDetailEntity shipping = this.shipmentDetailService.update(id,shipmentDetailDto);
        return ResponseEntity.ok(shipping);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        if(this.shipmentDetailService.existsByIdDetail(id)){
            this.shipmentDetailService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
