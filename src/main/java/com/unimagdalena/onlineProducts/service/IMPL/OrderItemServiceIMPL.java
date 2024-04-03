package com.unimagdalena.onlineProducts.service.IMPL;

import com.unimagdalena.onlineProducts.persistence.DTO.OrderItemDto;
import com.unimagdalena.onlineProducts.persistence.entity.OrderItemEntity;
import com.unimagdalena.onlineProducts.persistence.mapper.OrderItemMapper;
import com.unimagdalena.onlineProducts.persistence.repository.OrderItemRepository;
import com.unimagdalena.onlineProducts.service.OrderItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class OrderItemServiceIMPL implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    public OrderItemServiceIMPL(OrderItemRepository orderItemRepository, OrderItemMapper orderItemMapper) {
        this.orderItemRepository = orderItemRepository;
        this.orderItemMapper = orderItemMapper;
    }

    @Override
    public List<OrderItemDto> getAll() {
        List<OrderItemDto> orderItemDtos = this.orderItemRepository.findAll()
                .stream().map(order -> this.orderItemMapper.orderItemEntityToOrderItemDto(order))
                .collect(Collectors.toList());
        return orderItemDtos;
    }

    @Override
    public List<OrderItemDto> findAllByOrderId(int orderId) {
        List<OrderItemDto> orderItemDtos = this.orderItemRepository.findAllByOrderId(orderId)
                .stream().map(orderItem -> this.orderItemMapper.orderItemEntityToOrderItemDto(orderItem))
                .collect(Collectors.toList());
        return orderItemDtos;
    }

    @Override
    public List<OrderItemDto> findAllByProductId(int productId) {
        List<OrderItemDto> orderItemDtos = this.orderItemRepository.findAllByProductId(productId)
                .stream().map(orderItem -> this.orderItemMapper.orderItemEntityToOrderItemDto(orderItem))
                .collect(Collectors.toList());
        return orderItemDtos;
    }

    @Override
    public boolean existsByIdOrderItem(int id) {
        return this.orderItemRepository.existsByIdOrderItem(id);
    }

    @Override
    public OrderItemDto findByIdOrderItem(int id) {
        OrderItemEntity orderItemEntity = this.orderItemRepository.findByIdOrderItem(id);
        OrderItemDto orderItemDto = this.orderItemMapper.orderItemEntityToOrderItemDto(orderItemEntity);
        return orderItemDto;
    }

    @Override
    public Double sumTotalSalesByProductId(Long productId) {
        Double suma = this.orderItemRepository.sumTotalSalesByProductId(productId);
        return suma;
    }

    @Override
    public OrderItemEntity save(OrderItemEntity orderItemEntity) {
        return this.orderItemRepository.save(orderItemEntity);
    }

    @Override
    public OrderItemEntity update(int id, OrderItemDto orderItemDto) {
        Optional<OrderItemEntity> optionalOrderItem = this.orderItemRepository.findById((long) id);
        if(optionalOrderItem.isPresent()){
            OrderItemEntity existOrderItem = optionalOrderItem.get();
            BeanUtils.copyProperties(orderItemDto,existOrderItem);
            return this.orderItemRepository.save(existOrderItem);
        }
        throw new RuntimeException("Order Not found whit that Id");
    }

    @Override
    public void delete(int id) {
        this.orderItemRepository.deleteById(Long.valueOf(id));
    }
}
