package com.nhnacademy.eggplantdelivery.constant;

/**
 * @author : 조재철
 * @since 1.0
 */
public enum RoutingKeyConstant {

    ROUTING_REQUEST_TRACKING_NO("routing.RequestTrackingNo"),
    ROUTING_RESPONSE_TRACKING_NO("routing.ResponseTrackingNo"),
    ROUTING_CHANGE_DELIVERY_STATUS("routing.ChangeDeliveryStatus");

    private final String value;

    RoutingKeyConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
