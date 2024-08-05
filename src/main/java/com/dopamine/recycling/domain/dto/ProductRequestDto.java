package com.dopamine.recycling.domain.dto;

import com.dopamine.recycling.domain.entity.Product;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDto {
    private String name;
    private Long price;
    private String content;
    private String imagePath;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long marks;

    public Product toEntity() {
        return Product.builder()
                .name(name)
                .price(price)
                .content(content)
                .imagePath(imagePath)
                .createdAt(LocalDateTime.now())
                .updatedAt(null)
                .marks(0L)
                .build();
    }
}