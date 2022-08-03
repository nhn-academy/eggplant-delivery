package com.nhnacademy.eggplantdelivery.dto.request;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 주문정보 요청 Dto 입니다.
 *
 * @version 1.0.0
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfoRequestDto {

    private UUID trackingNo;
    private String receiverName;
    private String receiverAddress;
    private String receiverPhone;
    private String orderNo;
    private String shopHost;
    private Integer shopPort;

    public void insertTrackingNo(final UUID createdTrackingNo) {
        this.trackingNo = createdTrackingNo;
    }

    public void insertShopHost(final String shopHost) {
        this.shopHost = shopHost;
    }

    public void insertShopPort(final Integer shopPort) {
        this.shopPort = shopPort;
    }
}
