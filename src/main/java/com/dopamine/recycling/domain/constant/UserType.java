package com.dopamine.recycling.domain.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum UserType {
    USER_SEED("씨앗"),
    USER_SPROUT("새싹"),
    USER_FLOWER("꽃"),
    USER_TREE("나무");

    private String userType;
}
