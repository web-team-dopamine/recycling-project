package com.dopamine.recycling.domain.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum DeliveryStatus {
    PREPARING("배송 준비중"),
    DELIVERING("배송중"),
    COMPLETE("배송 완료");

    private String deliveryStatus;
}
