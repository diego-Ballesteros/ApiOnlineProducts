package com.unimagdalena.onlineProducts.service.IMPL;

import com.unimagdalena.onlineProducts.persistence.DTO.ShipmentDetailDto;
import com.unimagdalena.onlineProducts.persistence.entity.OrderEntity;
import com.unimagdalena.onlineProducts.persistence.entity.ShipmentDetailEntity;
import com.unimagdalena.onlineProducts.persistence.mapper.ShipmentDetailMapper;
import com.unimagdalena.onlineProducts.persistence.repository.ShipmentDetailRepository;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ShipmentDetailServiceIMPLTest {
    @InjectMocks
    ShipmentDetailServiceIMPL shipmentDetailServiceIMPL;
    @Mock
    ShipmentDetailMapper shipmentDetailMapper;
    @Mock
    ShipmentDetailRepository shipmentDetailRepository;

    ShipmentDetailEntity shipmentDetail1, shipmentDetail2;
    ShipmentDetailDto shipmentDetailDto1, shipmentDetailDto2, updateShipmentDto;

    List<ShipmentDetailEntity> listShipmentDetailsEntity = new ArrayList<>();
    List<ShipmentDetailEntity> listShipmentCarrierEntity = new ArrayList<>();
    List<ShipmentDetailDto> listExpectCarrierDto = new ArrayList<>();
    List<ShipmentDetailDto> listExpectedShipmentDto = new ArrayList<>();
    @BeforeEach
    void setUp(){
        shipmentDetail1 = ShipmentDetailEntity.builder()
                .idDetail(1L)
                .address("Concepcion 4")
                .carrier("hs24")
                .guideNumber(123456789L)
                .orderId(1l)
                .order(OrderEntity.builder()
                        .idOrder(1l).build())
                .build();
        shipmentDetail2 = ShipmentDetailEntity.builder()
                .idDetail(2L)
                .address("Concepcion 5")
                .carrier("servientrega")
                .guideNumber(987654321L)
                .orderId(1l)
                .order(OrderEntity.builder()
                        .idOrder(1l).build())
                .build();
        shipmentDetailDto1 = ShipmentDetailDto.builder()
                .address("Concepcion 4")
                .carrier("hs24")
                .guideNumber(123456789L)
                .orderId(1l).build();
        shipmentDetailDto2 = ShipmentDetailDto.builder()
                .address("Concepcion 5")
                .carrier("servientrega")
                .guideNumber(987654321L)
                .orderId(1l).build();
        updateShipmentDto= ShipmentDetailDto.builder()
                .address("Alqueria la fragua")
                .carrier("hs24")
                .guideNumber(1000000L)
                .orderId(1l).build();

        listShipmentDetailsEntity.add(shipmentDetail1); listShipmentDetailsEntity.add(shipmentDetail2);
        listExpectedShipmentDto.add(shipmentDetailDto1); listExpectedShipmentDto.add(shipmentDetailDto2);
        listExpectCarrierDto.add(shipmentDetailDto2); listShipmentCarrierEntity.add(shipmentDetail2);
    }
    void shipmenMapperProces(){
        when(this.shipmentDetailMapper.ShipmentDetailEntityToShipmentDetailDto(shipmentDetail1)).thenReturn(shipmentDetailDto1);
        when(this.shipmentDetailMapper.ShipmentDetailEntityToShipmentDetailDto(shipmentDetail2)).thenReturn(shipmentDetailDto2);
    }
    @DisplayName("JUnit test for find all Shipment Method")
    @Test
    void getAll() {
        when(this.shipmentDetailRepository.findAll()).thenReturn(listShipmentDetailsEntity);
        shipmenMapperProces();

        List<ShipmentDetailDto> resultShipmentDetailDtos = this.shipmentDetailServiceIMPL.getAll();

        assertNotNull(resultShipmentDetailDtos);
        assertEquals(listExpectedShipmentDto, resultShipmentDetailDtos);
        assertEquals(listExpectedShipmentDto.size(), resultShipmentDetailDtos.size());
        assertEquals(listExpectedShipmentDto.get(0).getAddress(), resultShipmentDetailDtos.get(0).getAddress());
        assertEquals(listExpectedShipmentDto.get(1).getAddress(), resultShipmentDetailDtos.get(1).getAddress());
    }

    @DisplayName("JUnit test for find all by orderId Method")
    @Test
    void findAllByOrderId() {
        int idOrder = 1;
        when(this.shipmentDetailRepository.findAllByOrderId(idOrder)).thenReturn(listShipmentDetailsEntity);
        shipmenMapperProces();

        List<ShipmentDetailDto> resultDetailDtos = this.shipmentDetailServiceIMPL.findAllByOrderId(idOrder);

        assertNotNull(resultDetailDtos);
        assertEquals(listExpectedShipmentDto, resultDetailDtos);
        assertEquals(listExpectedShipmentDto.size(), resultDetailDtos.size());
        assertEquals(listExpectedShipmentDto.get(0).getOrderId(), resultDetailDtos.get(0).getOrderId());
        assertEquals(listExpectedShipmentDto.get(1).getOrderId(), resultDetailDtos.get(1).getOrderId());
    }

    @DisplayName("JUnit test for find all by carrier Method")
    @Test
    void findAllByCarrier() {
        String caarrier = "servientrega";
        when(this.shipmentDetailRepository.findAllByCarrier(caarrier)).thenReturn(listShipmentCarrierEntity);
        when(this.shipmentDetailMapper.ShipmentDetailEntityToShipmentDetailDto(shipmentDetail2)).thenReturn(shipmentDetailDto2);

        List<ShipmentDetailDto> resultShipmentDetailDtos = this.shipmentDetailServiceIMPL.findAllByCarrier(caarrier);

        assertNotNull(resultShipmentDetailDtos);
        assertEquals(listExpectCarrierDto.size(), resultShipmentDetailDtos.size());
        assertEquals(listExpectCarrierDto, resultShipmentDetailDtos);
        assertEquals(listExpectCarrierDto.get(0).getCarrier(), resultShipmentDetailDtos.get(0).getCarrier());
    }

    @DisplayName("JUnit test for exist by id shipment detail Method")
    @Test
    void existsByIdDetail() {
        int idDetail = 1;
        when(this.shipmentDetailRepository.existsByIdDetail(idDetail)).thenReturn(true);
        boolean result = this.shipmentDetailServiceIMPL.existsByIdDetail(idDetail);
        assertTrue(result);
    }

    @DisplayName("JUnit test for find by id Method")
    @Test
    void findByIdDetail() {
        int idDetail = 1;
        when(this.shipmentDetailRepository.findByIdDetail(idDetail)).thenReturn(shipmentDetail1);
        when(this.shipmentDetailMapper.ShipmentDetailEntityToShipmentDetailDto(shipmentDetail1)).thenReturn(shipmentDetailDto1);

        Optional<ShipmentDetailDto> resultShipmentDetailDto = this.shipmentDetailServiceIMPL.findByIdDetail(idDetail);

        assertTrue(resultShipmentDetailDto.isPresent());
        assertEquals(shipmentDetailDto1, resultShipmentDetailDto.get());
    }

    @DisplayName("JUnit test for save Method")
    @Test
    void save() {
        when(this.shipmentDetailRepository.save(shipmentDetail1)).thenReturn(shipmentDetail1);
        ShipmentDetailEntity savedShipmentDetail = this.shipmentDetailServiceIMPL.save(shipmentDetail1);
        assertNotNull(savedShipmentDetail);
        assertEquals(shipmentDetail1, savedShipmentDetail);
    }

    @DisplayName("JUnit test for update Method")
    @Test
    void update() {
        int idDetail = 1;
        when(this.shipmentDetailRepository.findById((long) idDetail)).thenReturn(Optional.ofNullable(shipmentDetail1));
        when(this.shipmentDetailRepository.save(shipmentDetail1)).thenReturn(shipmentDetail1);
        ShipmentDetailEntity update = this.shipmentDetailServiceIMPL.update(idDetail, updateShipmentDto);
        assertNotNull(update);
        assertEquals(updateShipmentDto.getGuideNumber(), update.getGuideNumber());
    }
    @DisplayName("JUnit test for update invalid Method")
    @Test
    void updateInvalid() {
        int idDetailInvalid = 999;
        when(this.shipmentDetailRepository.findById((long) idDetailInvalid)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> this.shipmentDetailServiceIMPL.update(idDetailInvalid, updateShipmentDto));
    }

    @DisplayName("JUnit test for delete Method")
    @Test
    void delete() {
        int idDetail = 1;
        this.shipmentDetailServiceIMPL.delete(idDetail);
        verify(this.shipmentDetailRepository).deleteById((long) idDetail);
    }
}