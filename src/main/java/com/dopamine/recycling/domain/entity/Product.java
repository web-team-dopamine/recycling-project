package com.dopamine.recycling.domain.entity;

import com.dopamine.recycling.domain.dto.ProductResponseDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String name;
    private Long price;
    private String content;
    private Long marks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String imagePath;
    private boolean isDenied;

    public ProductResponseDto toResponse() {
        return ProductResponseDto.builder()
                .id(id)
                .name(name)
                .price(price)
                .content(content)
                .marks(marks)
                .imagePath(imagePath)
                .marks(marks)
                .build();
    }
}
