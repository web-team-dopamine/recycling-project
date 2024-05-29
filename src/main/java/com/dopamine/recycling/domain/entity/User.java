package com.dopamine.recycling.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
    private String type;
    private Long point;
    private String address;
    private String postcode;
    private boolean isPaused;
    private LocalDateTime unpauseDate;
    private boolean isDeleted;
}
