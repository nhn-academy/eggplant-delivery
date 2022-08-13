package com.nhnacademy.eggplantdelivery.constant;

/**
 * @author : 조재철
 * @since 1.0
 */
public enum ExchangeConstant {

    DIRECT_EXCHANGE("exchange.direct");

    private final String value;

    ExchangeConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
