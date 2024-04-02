package com.unimagdalena.onlineProducts.persistence.mapper;

import com.unimagdalena.onlineProducts.persistence.DTO.ProductDto;
import com.unimagdalena.onlineProducts.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductEntity productDtoToProductEntity(ProductDto productDto);
    ProductDto productEntityToProductDto(ProductEntity productEntity);
}
