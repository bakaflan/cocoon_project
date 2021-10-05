package com.ruiling.cocoon.domain.order;

import lombok.Getter;

@Getter
public enum OrderStatus {
    CREATED("已创建");

    private final String message;

    OrderStatus(String message) {
        this.message = message;
    }
}
