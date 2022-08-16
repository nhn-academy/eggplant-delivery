package com.nhnacademy.eggplantdelivery.constant;

/**
 * Rabbit MQ RoutingKey 의 네이밍을 담고있는 enum 클래스 입니다.
 *
 * @author 김훈민, 조재철
 * @version 1.0.0
 */
public enum RoutingKeyConstant {

    ROUTING_REQUEST_TRACKING_NO("routing.RequestTrackingNo"),
    ROUTING_REQUEST_TRACKING_NO_DLX("routing.RequestTrackingNoDLX"),
    ROUTING_CHANGE_DELIVERY_STATUS("routing.ChangeDeliveryStatus");

    private final String value;

    RoutingKeyConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
