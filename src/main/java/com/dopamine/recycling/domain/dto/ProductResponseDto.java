package com.dopamine.recycling.domain.dto;

import com.dopamine.recycling.domain.entity.Product;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDto {
    private Long id;
    private Long userId;
    private String name;
    private Long price;
    private String content;
    private Long marks;
    private String imagePath;

    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.content = product.getContent();
        this.marks = product.getMarks();
        this.imagePath = product.getImagePath();
    }
}
