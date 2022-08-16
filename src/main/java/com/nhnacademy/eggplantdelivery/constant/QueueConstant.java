package com.nhnacademy.eggplantdelivery.constant;

/**
 * Rabbit MQ Queue 의 네이밍을 담고있는 enum 클래스 입니다.
 *
 * @author 김훈민, 조재철
 * @version 1.0.0
 */
public enum QueueConstant {

    REQUEST_TRACKING_NO("queue.RequestTrackingNo"),
    REQUEST_TRACKING_NO_DLX("queue.RequestTrackingNo.dlx"),
    CHANGE_DELIVERY_STATUS("queue.ChangeDeliveryStatus");

    private final String value;

    QueueConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
