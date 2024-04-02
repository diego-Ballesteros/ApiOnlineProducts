package com.unimagdalena.onlineProducts.service.IMPL;

import com.unimagdalena.onlineProducts.persistence.DTO.ProductDto;
import com.unimagdalena.onlineProducts.persistence.entity.ProductEntity;
import com.unimagdalena.onlineProducts.persistence.mapper.ProductMapper;
import com.unimagdalena.onlineProducts.persistence.repository.ProductRepository;
import com.unimagdalena.onlineProducts.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceIMPL implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceIMPL(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDto> findAllIsInStock() {
        List<ProductEntity> productEntities = this.productRepository.findAll();
        List<ProductEntity> productsStock = new ArrayList<>();

        productEntities.forEach(product -> {
            if(product.getStock() > 0){
                productsStock.add(product);
            }
        });

        List<ProductDto> productDtos = productsStock.stream()
                .map(product -> this.productMapper.productEntityToProductDto(product))
                .collect(Collectors.toList());

        return productDtos;
    }

    @Override
    // productos que no superen un precio dado y ademas que tenga el stock dado
    public List<ProductDto> finAllByPriceNotExceedPriceAndStock(Double price, int stock) {
        List<ProductEntity> productEntities = this.productRepository.findAll();
        List<ProductEntity> productsCheck = new ArrayList<>();

        productEntities.forEach(product -> {
            if(!(product.getPrice() > price) && product.getStock()>= stock ){
                productsCheck.add(product);
            }
        });

        List<ProductDto> productDtos = productsCheck.stream()
                .map(product -> this.productMapper.productEntityToProductDto(product))
                .collect(Collectors.toList());

        return productDtos;
    }

    @Override
    public ProductDto findByName(String name) {
        ProductEntity productEntity = this.productRepository.findByName(name);
        ProductDto productDto = this.productMapper.productEntityToProductDto(productEntity);
        return productDto;
    }

    @Override
    public ProductEntity save(ProductEntity productEntity) {
        return this.productRepository.save(productEntity);
    }

    @Override
    public Optional<ProductEntity> update(int id, ProductEntity newProduct) {
        return this.productRepository.findById((long) id)
                .map(olderProduct -> {
                    ProductEntity productEntity = olderProduct.update(newProduct);
                    return this.productRepository.save(productEntity);
                });
    }

    @Override
    public ProductEntity findByid(int id) {
        return (ProductEntity) this.productRepository.findByIdProduct(id);
    }

    @Override
    public boolean existById(int id) {
        return this.productRepository.existsById((long) id);
    }

    @Override
    public void deleteById(int id) {
        this.productRepository.deleteById((long) id);
    }
}
