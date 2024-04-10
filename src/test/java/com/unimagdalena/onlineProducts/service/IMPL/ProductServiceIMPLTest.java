package com.unimagdalena.onlineProducts.service.IMPL;

import com.unimagdalena.onlineProducts.persistence.DTO.ProductDto;
import com.unimagdalena.onlineProducts.persistence.entity.ProductEntity;
import com.unimagdalena.onlineProducts.persistence.mapper.ProductMapper;
import com.unimagdalena.onlineProducts.persistence.repository.ProductRepository;
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
class ProductServiceIMPLTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductMapper productMapper;
    @InjectMocks
    private ProductServiceIMPL productServiceIMPL;

    ProductEntity productEntity, productEntity2;
    ProductDto productDto, productDto2;
    List<ProductEntity>  productEntities = new ArrayList<>();
    List<ProductDto> expectedProductsDto = new ArrayList<>();

    @BeforeEach
    void setUp(){
        productEntity = ProductEntity.builder()
                .idProduct(1L)
                .name("Play 5")
                .price(800.0)
                .stock(200)
                .orderItems(new ArrayList<>())
                .build();
        productEntity2 = ProductEntity.builder()
                .idProduct(2L)
                .name("Play 4")
                .price(500.0)
                .stock(100)
                .orderItems(new ArrayList<>())
                .build();

        productDto = ProductDto.builder()
                .name("Play 5")
                .price(800.0)
                .stock(200)
                .build();
        productDto2 = ProductDto.builder()
                .name("Play 4")
                .price(500.0)
                .stock(100)
                .build();


        productEntities.add(productEntity); productEntities.add(productEntity2);
        expectedProductsDto.add(productDto); expectedProductsDto.add(productDto2);
    }
    @DisplayName("JUnit test for find all products in stock Method")
    @Test
    void whenfindAllProductsInStock_thenReturnListProductsInStock() {
        //
        when(this.productRepository.findAll()).thenReturn(productEntities);
        when(this.productMapper.productEntityToProductDto(productEntity)).thenReturn(productDto);
        when(this.productMapper.productEntityToProductDto(productEntity2)).thenReturn(productDto2);

        //
        List<ProductDto> resulProductsDto = this.productServiceIMPL.findAllIsInStock();
        //
        assertNotNull(resulProductsDto);
        assertEquals(expectedProductsDto, resulProductsDto);
        assertEquals(expectedProductsDto.size(), resulProductsDto.size());
        assertEquals(expectedProductsDto.get(0).getName(), resulProductsDto.get(0).getName());
        assertEquals(expectedProductsDto.get(1).getName(), resulProductsDto.get(1).getName());
    }
    @DisplayName("JUnit test for find all by price not exced price Method")
    @Test
    void givePriceAndStock_whenfinAllByPriceNotExceedPriceAndStock_thenReturnListProductsDto() {
        Double price = 900.0; int stock = 101;
        when(this.productRepository.findAll()).thenReturn(productEntities);
        when(this.productMapper.productEntityToProductDto(productEntity)).thenReturn(productDto);

        List<ProductDto> resultProductDtos = this.productServiceIMPL.finAllByPriceNotExceedPriceAndStock(price, stock);
        List<ProductDto> expectedProductsDtoLocal = new ArrayList<>();
        expectedProductsDtoLocal.add(productDto);

        assertEquals(1, resultProductDtos.size());
        assertEquals(expectedProductsDtoLocal, resultProductDtos);
        assertEquals(expectedProductsDtoLocal.get(0).getPrice(), resultProductDtos.get(0).getPrice());
        assertTrue(resultProductDtos.get(0).getPrice() < price);
        assertTrue(resultProductDtos.get(0).getStock() >= stock );
    }
    @DisplayName("JUnit test for findByName Method")
    @Test
    void givenProductName_whenfindByName_thenReturnOptionalProductDto() {
        String name  = "Play 5";
        when(this.productRepository.findByName(name)).thenReturn(productEntity);
        when(this.productMapper.productEntityToProductDto(productEntity)).thenReturn(productDto);

        Optional<ProductDto> resultProductDto = this.productServiceIMPL.findByName(name);

        assertTrue(resultProductDto.isPresent());
        assertEquals(productDto, resultProductDto.get());
        assertEquals(productDto.getName(), resultProductDto.get().getName());
        assertEquals(name, resultProductDto.get().getName());
    }
    @DisplayName("JUnit test for findById Method")
    @Test
    void givenProductId_whenFindByid_thenReturnOptionalProductDto() {
        Long id = 1l;
        when(this.productRepository.findByIdProduct(Math.toIntExact(id))).thenReturn(productEntity);
        when(this.productMapper.productEntityToProductDto(productEntity)).thenReturn(productDto);

        Optional<ProductDto> resultProductDto = this.productServiceIMPL.findByid(Math.toIntExact(id));

        assertTrue(resultProductDto.isPresent());
        assertEquals(productDto, resultProductDto.get());
        assertEquals(productDto.getName(), resultProductDto.get().getName());
    }
    @Test
    void existById() {
    }
    @Test
    void save() {
    }
    @Test
    void update() {
    }
    @Test
    void deleteById() {
    }
}