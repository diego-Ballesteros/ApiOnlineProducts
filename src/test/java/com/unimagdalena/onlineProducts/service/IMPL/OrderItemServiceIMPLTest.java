package com.unimagdalena.onlineProducts.service.IMPL;

import com.unimagdalena.onlineProducts.persistence.DTO.OrderItemDto;
import com.unimagdalena.onlineProducts.persistence.entity.OrderEntity;
import com.unimagdalena.onlineProducts.persistence.entity.OrderItemEntity;
import com.unimagdalena.onlineProducts.persistence.entity.ProductEntity;
import com.unimagdalena.onlineProducts.persistence.mapper.OrderItemMapper;
import com.unimagdalena.onlineProducts.persistence.repository.OrderItemRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class OrderItemServiceIMPLTest {
    @InjectMocks
    OrderItemServiceIMPL itemServiceIMPL;
    @Mock
    OrderItemMapper itemMapper;
    @Mock
    OrderItemRepository itemRepository;

    OrderItemEntity itemEntity1, itemEntity2;
    OrderItemDto itemDto1, itemDto2, updateItemDto;
    List<OrderItemEntity> itemEntityList = new ArrayList<>(), itemEntitybyProductIdList = new ArrayList<>();
    List<OrderItemDto> expectedItemDtos = new ArrayList<>(), itemDtobyProductIdList = new ArrayList<>();
    @BeforeEach
    void setUp(){
        itemEntity1 = OrderItemEntity.builder()
                .idOrderItem(1L)
                .quantity(5)
                .unitPrice(800.00)
                .orderId(1l)
                .order(OrderEntity.builder()
                        .idOrder(1l).build())
                .productId(1l)
                .product(ProductEntity.builder()
                        .idProduct(1l).build())
                .build();

        itemEntity2 = OrderItemEntity.builder()
                .idOrderItem(2L)
                .quantity(10)
                .unitPrice(500.00)
                .orderId(1l)
                .order(OrderEntity.builder()
                        .idOrder(1l).build())
                .productId(2l)
                .product(ProductEntity.builder()
                        .idProduct(2l).build())
                .build();
        itemDto1 = OrderItemDto.builder()
                .quantity(5)
                .unitPrice(800.00)
                .orderId(1l)
                .productId(1l)
                .build();
        itemDto2 = OrderItemDto.builder()
                .quantity(10)
                .unitPrice(500.00)
                .orderId(1l)
                .productId(2l)
                .build();
        updateItemDto = OrderItemDto.builder()
                .quantity(7)
                .unitPrice(950.00)
                .orderId(1l)
                .productId(1l)
                .build();

        itemEntityList.add(itemEntity1); itemEntityList.add(itemEntity2);
        expectedItemDtos.add(itemDto1); expectedItemDtos.add(itemDto2);
        itemEntitybyProductIdList.add(itemEntity2); itemDtobyProductIdList.add(itemDto2);
    }

    void itemMapperProcess(){
        when(this.itemMapper.orderItemEntityToOrderItemDto(itemEntity1)).thenReturn(itemDto1);
        when(this.itemMapper.orderItemEntityToOrderItemDto(itemEntity2)).thenReturn(itemDto2);
    }
    void assertListItem(List<OrderItemDto> resultItemDtos){
        assertNotNull(resultItemDtos);
        assertEquals(expectedItemDtos.size(), resultItemDtos.size());
        assertEquals(expectedItemDtos, resultItemDtos);
    }
    @DisplayName("Test for get all order items method")
    @Test
    void getAll() {
        when(this.itemRepository.findAll()).thenReturn(itemEntityList);
        itemMapperProcess();

        List<OrderItemDto> resultItemDtos = this.itemServiceIMPL.getAll();

        assertListItem(resultItemDtos);
        assertEquals(expectedItemDtos.get(0).getQuantity(), resultItemDtos.get(0).getQuantity());
    }

    @DisplayName("Test for find all id-order method")
    @Test
    void findAllByOrderId() {
        int idOrder = 1;
        when(this.itemRepository.findAllByOrderId(idOrder)).thenReturn(itemEntityList);
        itemMapperProcess();

        List<OrderItemDto> resulTItemDtos = this.itemServiceIMPL.findAllByOrderId(idOrder);

        assertListItem(resulTItemDtos);
        assertEquals(expectedItemDtos.get(0).getQuantity(), resulTItemDtos.get(0).getQuantity());
    }

    @DisplayName("Test for find all by id-product method")
    @Test
    void findAllByProductId() {
        int idProduct = 2;
        when(this.itemRepository.findAllByProductId(idProduct)).thenReturn(itemEntitybyProductIdList);
        when(this.itemMapper.orderItemEntityToOrderItemDto(itemEntity2)).thenReturn(itemDto2);

        List<OrderItemDto> resulTItemDtos = this.itemServiceIMPL.findAllByProductId(idProduct);

        assertFalse(resulTItemDtos.isEmpty());
        assertEquals(itemDtobyProductIdList.size(), resulTItemDtos.size() );
        assertEquals(itemDtobyProductIdList.get(0).getProductId(), resulTItemDtos.get(0).getProductId());
    }

    @DisplayName("Test for exist by id-OrderItem method")
    @Test
    void existsByIdOrderItem() {
        int idItem = 1;
        when(this.itemRepository.existsByIdOrderItem(idItem)).thenReturn(true);
        boolean result = this.itemServiceIMPL.existsByIdOrderItem(idItem);
        assertTrue(result);
    }

    @DisplayName("Test for find by id-order-item method")
    @Test
    void findByIdOrderItem() {
        int idItem = 2;
        when(this.itemRepository.findByIdOrderItem(idItem)).thenReturn(itemEntity2);
        when(this.itemMapper.orderItemEntityToOrderItemDto(itemEntity2)).thenReturn(itemDto2);

        Optional<OrderItemDto> resultItemDto = this.itemServiceIMPL.findByIdOrderItem(idItem);

        assertTrue(resultItemDto.isPresent());
        assertEquals(itemDto2, resultItemDto.get());
    }

    @DisplayName("Test for total suma by id-product method")
    @Test
    void sumTotalSalesByProductId() {
        Long idProduct = 1L;
        Double expectedSuma = 800.00 * 5;
        when(this.itemRepository.sumTotalSalesByProductId(idProduct)).thenReturn(expectedSuma);

        Double resultSuma = this.itemServiceIMPL.sumTotalSalesByProductId(idProduct);

        assertNotNull(resultSuma);
        assertEquals(expectedSuma, resultSuma);
    }

    @DisplayName("Test for save order items method")
    @Test
    void save() {
        when(this.itemRepository.save(itemEntity1)).thenReturn(itemEntity1);
        OrderItemEntity savedItemEntity = this.itemServiceIMPL.save(itemEntity1);
        assertNotNull(savedItemEntity);
        assertEquals(itemEntity1.getIdOrderItem(), savedItemEntity.getIdOrderItem());
    }

    @DisplayName("Test for update order items method")
    @Test
    void update() {
        int idItem = 1;
        when(this.itemRepository.findById((long) idItem)).thenReturn(Optional.ofNullable(itemEntity1));
        when(this.itemRepository.save(itemEntity1)).thenReturn(itemEntity1);

         OrderItemEntity resultUpdateItemEntity = this.itemServiceIMPL.update(idItem, updateItemDto);

         assertNotNull(resultUpdateItemEntity);
         assertEquals(950.00, resultUpdateItemEntity.getUnitPrice());
        assertEquals(7, resultUpdateItemEntity.getQuantity());
    }
    @DisplayName("Test for invalid update order items method")
    @Test
    void updateInvalid() {
        int idItemInvalid = 999;
        when(this.itemRepository.findById((long) idItemInvalid)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, ()-> this.itemServiceIMPL.update(idItemInvalid, updateItemDto));
    }

    @DisplayName("Test for get all order items method")
    @Test
    void delete() {
        int idItem = 1;
        this.itemServiceIMPL.delete(idItem);
        verify(this.itemRepository).deleteById((long) idItem);
    }
}