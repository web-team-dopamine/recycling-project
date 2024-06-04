package com.dopamine.recycling.domain.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

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
}
