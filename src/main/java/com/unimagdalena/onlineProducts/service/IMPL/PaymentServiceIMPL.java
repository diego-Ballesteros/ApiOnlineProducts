package com.unimagdalena.onlineProducts.service.IMPL;

import com.unimagdalena.onlineProducts.persistence.DTO.PaymentDto;
import com.unimagdalena.onlineProducts.persistence.entity.PaymentEntity;
import com.unimagdalena.onlineProducts.persistence.mapper.PaymentMapper;
import com.unimagdalena.onlineProducts.persistence.repository.PaymentRepository;
import com.unimagdalena.onlineProducts.service.PaymentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentServiceIMPL implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public PaymentServiceIMPL(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }
    @Override
    public List<PaymentDto> getAll() {
        List<PaymentDto> paymentDtos = this.paymentRepository.findAll()
                .stream().map(payment -> this.paymentMapper.paymentEntityToPaymentDto(payment))
                .collect(Collectors.toList());
        return paymentDtos;
    }
    @Override
    public Optional<PaymentDto> getById(int id) {
        PaymentEntity paymentEntity = this.paymentRepository.findByIdPayment(id);
        PaymentDto paymentDto = this.paymentMapper.paymentEntityToPaymentDto(paymentEntity);
        return Optional.ofNullable(paymentDto);
    }
    @Override
    public boolean existsByIdPayment(int id) {
        return this.paymentRepository.existsByIdPayment(id);
    }
    @Override
    public List<PaymentDto> findAllByPaymentDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        List<PaymentDto> paymentDtos = this.paymentRepository.findAllByPaymentDateBetween(startDate,endDate)
                .stream().map(payment -> this.paymentMapper.paymentEntityToPaymentDto(payment))
                .collect(Collectors.toList());
        return paymentDtos;
    }
    @Override
    public PaymentEntity save(PaymentEntity paymentEntity) {
        return this.paymentRepository.save(paymentEntity);
    }
    @Override
    public PaymentEntity update(int id, PaymentDto paymentDto) {
        Optional<PaymentEntity> optionalPayment = this.paymentRepository.findById((long) id);
        if(optionalPayment.isPresent()){
            PaymentEntity existPayment = optionalPayment.get();
            BeanUtils.copyProperties(paymentDto, existPayment);
            return this.paymentRepository.save(existPayment);
        }
        throw  new RuntimeException("Payment NOt found with that Id");
    }
    @Override
    public void delete(int id) {
        this.paymentRepository.deleteById((long) id);
    }
    @Override
    public boolean existById(int id) {
        return this.paymentRepository.existsById((long)id);
    }
}
