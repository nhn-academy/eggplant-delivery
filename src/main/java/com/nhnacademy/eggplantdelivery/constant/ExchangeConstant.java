package com.nhnacademy.eggplantdelivery.constant;

/**
 * Rabbit MQ Exchange 의 네이밍을 담고있는 enum 클래스 입니다.
 *
 * @author 김훈민, 조재철
 * @version 1.0.0
 */
public enum ExchangeConstant {

    DIRECT_EXCHANGE("exchange.direct"),
    DIRECT_EXCHANGE_DLX("exchange.direct.dlx");

    private final String value;

    ExchangeConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
