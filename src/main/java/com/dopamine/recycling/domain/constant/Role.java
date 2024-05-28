package com.dopamine.recycling.domain.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Role {
    ROLE_USER("사용자"),
    ROLE_SELLER("판매자"),
    ROLE_ADMIN("관리자");

    private String name;
}
