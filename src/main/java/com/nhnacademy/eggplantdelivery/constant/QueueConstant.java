package com.nhnacademy.eggplantdelivery.constant;

/**
 * @author : 조재철
 * @since 1.0
 */
public enum QueueConstant {

    REQUEST_TRACKING_NO("queue.RequestTrackingNo"),
    RESPONSE_TRACKING_NO("queue.ResponseTrackingNo"),
    CHANGE_DELIVERY_STATUS("queue.ChangeDeliveryStatus");

    private final String value;

    QueueConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
