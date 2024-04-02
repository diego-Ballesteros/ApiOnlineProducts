package com.unimagdalena.onlineProducts.service;

import com.unimagdalena.onlineProducts.persistence.DTO.ProductDto;
import com.unimagdalena.onlineProducts.persistence.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDto> findAllIsInStock();
    List<ProductDto> finAllByPriceNotExceedPriceAndStock(Double price, int stock);
    ProductDto findByName(String name);

    ProductEntity save(ProductEntity productEntity);
    Optional<ProductEntity> update(int id, ProductEntity product);

    ProductEntity findByid(int id);

    boolean existById (int id);

    void deleteById(int id);
}
