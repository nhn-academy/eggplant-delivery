package com.nhnacademy.eggplantdelivery.entity.status;

/**
 * @author : 조재철
 * @since 1.0
 */
public enum LocationStatus {

    FIRST_LOCATION("창원"),
    SECOND_LOCATION("김해");

    private final String value;

    LocationStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
