package com.unimagdalena.onlineProducts.persistence.mapper;

import com.unimagdalena.onlineProducts.persistence.DTO.CustomerDto;
import com.unimagdalena.onlineProducts.persistence.entity.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDto customerEntityToCustomerDto (CustomerEntity customerEntity);
    CustomerEntity customerDtoToCustomerEntity(CustomerDto customerDto);
}
