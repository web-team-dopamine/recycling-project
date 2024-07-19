package com.dopamine.recycling.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "`like`")
@Getter
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long reviewId;
}
