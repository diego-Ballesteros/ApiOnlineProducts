package com.unimagdalena.onlineProducts.service.IMPL;

import com.unimagdalena.onlineProducts.persistence.DTO.ProductDto;
import com.unimagdalena.onlineProducts.persistence.entity.CustomerEntity;
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
    ProductDto productDto, productDto2, updateproductDto;
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
        updateproductDto = ProductDto.builder()
                .name("updateName")
                .price(700.0)
                .stock(500)
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
        when(this.productMapper.productDtoToProductEntity(productDto)).thenReturn(productEntity);

        Optional<ProductDto> resultProductDto = this.productServiceIMPL.findByid(Math.toIntExact(id));

        assertTrue(resultProductDto.isPresent());
        assertEquals(productDto, resultProductDto.get());
        assertEquals(productDto.getName(), resultProductDto.get().getName());
        assertEquals(id, this.productMapper.productDtoToProductEntity(resultProductDto.get()).getIdProduct());
    }
    @DisplayName("JUnit test for exist By id Product Method")
    @Test
    void givenIdProduct_whenExistById_returnTrue() {
        int id = 1;
        when(this.productRepository.existsById((long) id)).thenReturn(true);

        boolean result = this.productServiceIMPL.existById(id);

        assertTrue(result);
    }
    @DisplayName("JUnit test for exist By invalid id Product Method")
    @Test
    void givenIdProduct_whenExistById_returnFalse() {
        int invalidId = 999;
        when(this.productRepository.existsById((long) invalidId)).thenReturn(false);

        boolean result = this.productServiceIMPL.existById(invalidId);

        assertFalse(result);
    }
    @DisplayName("JUnit test for save Product Method")
    @Test
    void givenProductEntity_whenSave_thenReturnSavedProductEntity() {
        when(this.productRepository.save(productEntity)).thenReturn(productEntity);

        ProductEntity savedProduct = this.productServiceIMPL.save(productEntity);

        assertNotNull(savedProduct);
        assertEquals(productEntity, savedProduct);
        assertEquals(productEntity.getIdProduct(), savedProduct.getIdProduct());
    }
    @DisplayName("JUnit test for update Product Method")
    @Test
    void givenIdProductAndProductDto_whenUpdate_thenReturnProductUpdated() {
    int id = 1;
        when(this.productRepository.findById((long) id)).thenReturn(Optional.of(productEntity));
        when(this.productRepository.save(productEntity)).thenReturn(productEntity);

        ProductEntity resultProduct = this.productServiceIMPL.update(id,updateproductDto);

        assertNotNull(resultProduct);
        assertEquals(updateproductDto.getName(), resultProduct.getName());
        assertEquals(updateproductDto.getPrice(), resultProduct.getPrice());
    }
    @DisplayName("JUnit test for update invalid Id Product")
    @Test
    void givenInvalidIdProductAndProductDto_whenUpdate_thenThrowException() {
        int invalidId = 999;
        when(this.productRepository.findById((long) invalidId)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> this.productServiceIMPL.update(invalidId, updateproductDto));
    }
    @DisplayName("JUnit test for delete Product Method")
    @Test
    void givenValidId_whenDeleteById_thenProductIsDeleted() {
        int productId = 1;
        this.productServiceIMPL.deleteById(productId);
        verify(this.productRepository).deleteById((long) productId);
    }
}