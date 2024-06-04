package com.dopamine.recycling.domain.entity;

import com.dopamine.recycling.domain.dto.UserResponseDto;
import jakarta.persistence.*;
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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nickname;
    private String email;
    private String password;
    private String role;
    private String type;
    private Long point;
    private String address;
    private String postcode;
    private boolean isPaused;
    private LocalDateTime unpauseDate;
    private LocalDateTime deleteDate;

    public UserResponseDto toResponse() {
        return UserResponseDto.builder()
                .id(id)
                .name(name)
                .nickname(nickname)
                .email(email)
                .password(password)
                .role(role)
                .type(type)
                .point(point)
                .address(address)
                .postcode(postcode)
                .isPaused(isPaused)
                .unpauseDate(unpauseDate)
                .deleteDate(deleteDate)
                .build();
    }
}
