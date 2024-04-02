package com.unimagdalena.onlineProducts.persistence.repository;

import com.unimagdalena.onlineProducts.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    ProductEntity findByName(String name);
    ProductEntity findByIdProduct(int id);
}
