package com.dopamine.recycling.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

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
