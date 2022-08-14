package com.nhnacademy.eggplantdelivery.exception;

public class LocationNotFoundException extends IllegalArgumentException {

    private static final String MESSAGE = "해당 배송 위치는 없습니다.";

    public LocationNotFoundException() {
        super(MESSAGE);
    }
}
