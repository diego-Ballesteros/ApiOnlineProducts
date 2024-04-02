package com.unimagdalena.onlineProducts.controller;

import com.unimagdalena.onlineProducts.persistence.DTO.ProductDto;
import com.unimagdalena.onlineProducts.persistence.entity.ProductEntity;
import com.unimagdalena.onlineProducts.persistence.mapper.ProductMapper;
import com.unimagdalena.onlineProducts.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    private final ProductMapper productMapper;
    private final ProductService productService;

    public ProductController(ProductMapper productMapper, ProductService productService) {
        this.productMapper = productMapper;
        this.productService = productService;
    }

    @GetMapping("/in-stock")
    public ResponseEntity<List<ProductDto>> getAllInStock(){
        return ResponseEntity.ok(this.productService.findAllIsInStock());
    }
    @GetMapping("/price-stock")
    public ResponseEntity<List<ProductDto>> getAllPriceNotExceedPriceAndStock(@RequestParam Double price, @RequestParam int stock){
        return ResponseEntity.ok(this.productService.finAllByPriceNotExceedPriceAndStock(price, stock));
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable int id){
        ProductDto productDto = this.productMapper.productEntityToProductDto(this.productService.findByid(id));
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/name")
    public ResponseEntity<ProductDto> findAllById(@RequestParam String name){
        return ResponseEntity.ok(this.productService.findByName(name));
    }

    @PostMapping()
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto){
        ProductEntity productEntity = this.productMapper.productDtoToProductEntity(productDto);
        if(productEntity.getIdProduct()==null || !this.productService.existById(Math.toIntExact(productEntity.getIdProduct()))) {
            ProductEntity productCreated = this.productService.save(productEntity);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{idProduct}")
                    .buildAndExpand(productCreated.getIdProduct())
                    .toUri();
            ProductDto productDto1 = this.productMapper.productEntityToProductDto(productCreated);
            return ResponseEntity.created(location).body(productDto1);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductEntity> update(@PathVariable("id") int id, @RequestBody ProductDto uProductDto){
        ProductEntity uProduct = this.productMapper.productDtoToProductEntity(uProductDto);
        Optional<ProductEntity> updateProduct = this.productService.update(id, uProduct);
        return updateProduct.map(product -> ResponseEntity.ok().body(product))
                .orElseGet(()-> {
                    ProductEntity newProductEntity = this.productService.save(uProduct);
                    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{idProduct}")
                            .buildAndExpand(newProductEntity.getIdProduct())
                            .toUri();
                    return ResponseEntity.created(location).body(newProductEntity);
                });
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        if(this.productService.existById(id)){
            this.productService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
