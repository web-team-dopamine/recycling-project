package com.dopamine.recycling.service;

import com.dopamine.recycling.domain.dto.ProductRequestDto;
import com.dopamine.recycling.domain.entity.Product;
import com.dopamine.recycling.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product save(ProductRequestDto product) {
        return productRepository.save(product.toEntity());
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getProductsAvailable() {
        return productRepository.findProductsAvailable();
    }

    public void updateProductById(long id, ProductRequestDto request) {
        Product product = productRepository.findById(id).orElse(null);

        if (product != null) {
            return;
        }

        productRepository.updateProductById(id, request.getName(), request.getPrice(), request.getContent(), request.getImagePath(), LocalDateTime.now());
    }

    public void deleteProductById(long id) {
        Product product = productRepository.findById(id).orElse(null);

        if(product == null) {
            return;
        }

        productRepository.deleteById(id);
    }
}
