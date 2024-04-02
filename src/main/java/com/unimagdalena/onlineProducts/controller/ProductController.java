package com.unimagdalena.onlineProducts.controller;

import com.unimagdalena.onlineProducts.persistence.DTO.ProductDto;
import com.unimagdalena.onlineProducts.persistence.mapper.ProductMapper;
import com.unimagdalena.onlineProducts.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
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

    @GetMapping("/find-id")
    public ResponseEntity<ProductDto> findAllById(@RequestParam int id){
        ProductDto productDto = this.productMapper.productEntityToProductDto(this.productService.findByid(id));
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/find-name")
    public ResponseEntity<ProductDto> findAllById(@RequestParam String name){
        return ResponseEntity.ok(this.productService.findByName(name));
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
