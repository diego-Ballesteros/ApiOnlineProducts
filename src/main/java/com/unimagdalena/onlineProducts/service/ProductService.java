package com.unimagdalena.onlineProducts.service;

import com.unimagdalena.onlineProducts.persistence.DTO.ProductDto;
import com.unimagdalena.onlineProducts.persistence.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAllIsInStock();
    List<ProductDto> finAllByPriceNotExceedPriceAndStock(Double price, int stock);
    ProductDto findByName(String name);

    ProductEntity save(ProductEntity productEntity);

    ProductEntity findByid(int id);

    boolean existById (int id);

    void deleteById(int id);
}
