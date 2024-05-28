package com.dopamine.recycling.domain.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum PostType {
    PROOF("인증"),
    PROMOTION("홍보"),
    COMPLAIN("신고");

    private String postType;
}
