package com.unimagdalena.onlineProducts.service.IMPL;

import com.unimagdalena.onlineProducts.persistence.DTO.OrderDto;
import com.unimagdalena.onlineProducts.persistence.entity.CustomerEntity;
import com.unimagdalena.onlineProducts.persistence.entity.OrderEntity;
import com.unimagdalena.onlineProducts.persistence.mapper.OrderMapper;
import com.unimagdalena.onlineProducts.persistence.repository.OderRepository;
import com.unimagdalena.onlineProducts.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class OrderServiceIMPL implements OrderService {

    private final OderRepository oderRepository;
    private final OrderMapper orderMapper;

    public OrderServiceIMPL(OderRepository oderRepository, OrderMapper orderMapper) {
        this.oderRepository = oderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<OrderDto> findAllByOderDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        List<OrderDto> orderDtos = this.oderRepository.findAllByOderDateBetween(startDate, endDate)
                .stream().map(order-> this.orderMapper.orderEntityToOrderDto(order))
                .collect(Collectors.toList());
        return orderDtos;
    }

    @Override
    public List<OrderDto> findAllByCustomerAndStatus(CustomerEntity customer, OrderEntity.Status status) {
        List<OrderDto> orderDtos = this.oderRepository.findAllByCustomerAndStatus(customer,status)
                .stream().map(order-> this.orderMapper.orderEntityToOrderDto(order))
                .collect(Collectors.toList());
        return orderDtos;
    }

    @Override
    public List<OrderDto> findAll() {
        List<OrderDto> orderDtos = this.oderRepository.findAll()
                .stream().map(order -> this.orderMapper.orderEntityToOrderDto(order))
                .collect(Collectors.toList());
        return orderDtos;
    }

    @Override
    public OrderDto getById(int id) {
       OrderEntity orderEntity = this.oderRepository.findByIdOrder(id);
       OrderDto orderDto = this.orderMapper.orderEntityToOrderDto(orderEntity);
       return orderDto;

    }

    @Override
    public OrderEntity save(OrderEntity orderEntity) {
        return this.oderRepository.save(orderEntity);
    }

    @Override
    public OrderEntity update(int id, OrderDto orderDto) {
        Optional<OrderEntity> optionalOrder = this.oderRepository.findById(Long.valueOf(id));
        if(optionalOrder.isPresent()){
            OrderEntity existOrder = optionalOrder.get();
            BeanUtils.copyProperties(orderDto,existOrder);
            return this.oderRepository.save(existOrder);
        }
        throw new RuntimeException("Order Not found whit that Id");
    }

    @Override
    public List<OrderEntity> findByCustomerId(int idCostumer) {
        return this.oderRepository.findByCustomerId(idCostumer);
    }

    @Override
    public void delete(int id) {
        this.oderRepository.deleteById((long) id);
    }

    @Override
    public boolean existById(int id) {
        return this.oderRepository.existsById((long) id);
    }
}
