package com.nhnacademy.eggplantdelivery.exception;

/**
 * 배송정보 Repository 에서 배송정보가 없을시 에러를 처리하는 클래스 입니다.
 */
public class DeliveryInfoNotFoundException extends IllegalArgumentException {

    private static final String MESSAGE = "해당 배송정보는 없습니다.";

    public DeliveryInfoNotFoundException() {
        super(MESSAGE);
    }

}
