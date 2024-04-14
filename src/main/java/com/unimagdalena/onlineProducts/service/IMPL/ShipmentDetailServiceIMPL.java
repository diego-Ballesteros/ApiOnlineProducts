package com.unimagdalena.onlineProducts.service.IMPL;

import com.unimagdalena.onlineProducts.persistence.DTO.ShipmentDetailDto;
import com.unimagdalena.onlineProducts.persistence.entity.ShipmentDetailEntity;
import com.unimagdalena.onlineProducts.persistence.mapper.ShipmentDetailMapper;
import com.unimagdalena.onlineProducts.persistence.repository.ShipmentDetailRepository;
import com.unimagdalena.onlineProducts.service.ShipmentDetailService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class ShipmentDetailServiceIMPL implements ShipmentDetailService {

    private final ShipmentDetailMapper shipmentDetailMapper;
    private final ShipmentDetailRepository shipmentDetailRepository;

    public ShipmentDetailServiceIMPL(ShipmentDetailMapper shipmentDetailMapper, ShipmentDetailRepository shipmentDetailRepository) {
        this.shipmentDetailMapper = shipmentDetailMapper;
        this.shipmentDetailRepository = shipmentDetailRepository;
    }

    @Override
    public List<ShipmentDetailDto> getAll() {
        List<ShipmentDetailDto> shipmentDetailDtos = this.shipmentDetailRepository.findAll()
                .stream().map(shipment -> this.shipmentDetailMapper.ShipmentDetailEntityToShipmentDetailDto(shipment))
                .collect(Collectors.toList());
        return shipmentDetailDtos;
    }

    @Override
    public List<ShipmentDetailDto> findAllByOrderId(int orderId) {
        List<ShipmentDetailDto> shipmentDetailDtos = this.shipmentDetailRepository.findAllByOrderId(orderId)
                .stream().map(shipment -> this.shipmentDetailMapper.ShipmentDetailEntityToShipmentDetailDto(shipment))
                .collect(Collectors.toList());
        return shipmentDetailDtos;
    }

    @Override
    public List<ShipmentDetailDto> findAllByCarrier(String carrier) {
        List<ShipmentDetailDto> shipmentDetailDtos = this.shipmentDetailRepository.findAllByCarrier(carrier)
                .stream().map(shipment -> this.shipmentDetailMapper.ShipmentDetailEntityToShipmentDetailDto(shipment))
                .collect(Collectors.toList());
        return shipmentDetailDtos;
    }

    @Override
    public boolean existsByIdDetail(int id) {
        return this.shipmentDetailRepository.existsByIdDetail(id);
    }

    @Override
    public Optional<ShipmentDetailDto> findByIdDetail(int id) {
        ShipmentDetailEntity shipmentDetailEntity = this.shipmentDetailRepository.findByIdDetail(id);
        ShipmentDetailDto shipmentDetailDto = this.shipmentDetailMapper.ShipmentDetailEntityToShipmentDetailDto(shipmentDetailEntity);
        return Optional.ofNullable(shipmentDetailDto);
    }

    @Override
    public ShipmentDetailEntity save(ShipmentDetailEntity shipmentDetailEntity) {
        return this.shipmentDetailRepository.save(shipmentDetailEntity);
    }

    @Override
    public ShipmentDetailEntity update(int id, ShipmentDetailDto shipmentDetailDto) {
        Optional<ShipmentDetailEntity> optionalShipment = this.shipmentDetailRepository.findById(Long.valueOf(id));
        if(optionalShipment.isPresent()){
            ShipmentDetailEntity existShipment = optionalShipment.get();
            BeanUtils.copyProperties(shipmentDetailDto,existShipment);
            return this.shipmentDetailRepository.save(existShipment);
        }
        throw new RuntimeException("Order Not found whit that Id");
    }

    @Override
    public void delete(int id) {
        this.shipmentDetailRepository.deleteById((long) id);
    }
}
