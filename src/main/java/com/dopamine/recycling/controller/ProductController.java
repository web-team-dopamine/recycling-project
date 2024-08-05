package com.dopamine.recycling.controller;

import com.dopamine.recycling.domain.dto.ProductRequestDto;
import com.dopamine.recycling.domain.dto.ProductResponseDto;
import com.dopamine.recycling.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.dopamine.recycling.domain.entity.Product;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto request) {
        Product product = productService.save(request);
        ProductResponseDto response = product.toResponse();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> findAllProducts() {
        List<Product> productList = productService.getAllProducts();

        if(productList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<ProductResponseDto> responseList = productList.stream()
                .map(ProductResponseDto::new)
                .toList();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/available")
    public ResponseEntity<List<ProductResponseDto>> findProductsAvailable() {
        List<Product> productList = productService.getProductsAvailable();

        if(productList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<ProductResponseDto> responseList = productList.stream()
                .map(ProductResponseDto::new)
                .toList();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> findProductById(@PathVariable("id") Long id) {
        Product product = productService.getProductById(id);

        if(product == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new ProductResponseDto(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable("id") long id, @RequestBody ProductRequestDto request) {
        try {
            productService.updateProductById(id, request);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") long id) {
        productService.deleteProductById(id);
        return ResponseEntity.ok().build();
    }
}
