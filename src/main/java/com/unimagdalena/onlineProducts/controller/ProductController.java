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
    public ResponseEntity<Optional<ProductDto>> getById(@PathVariable int id){
        Optional<ProductDto> productDto = this.productService.findByid(id);
        if(productDto.isPresent()){
            return ResponseEntity.ok(productDto);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/name")
    public ResponseEntity<Optional<ProductDto>> findAllByName(@RequestParam String name){
        Optional<ProductDto> productDto = this.productService.findByName(name);
        if(productDto.isPresent()){
            return ResponseEntity.ok(productDto);
        }
        return ResponseEntity.badRequest().build();
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
        ProductEntity uProduct = this.productService.update(id, uProductDto);
        return ResponseEntity.ok(uProduct);
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
