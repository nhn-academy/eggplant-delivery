package com.nhnacademy.eggplantdelivery.dto.request;

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

    private Long trackingNo;
    private String receiverName;
    private String receiverAddress;
    private String receiverPhone;
    private String shopHost;
    private String orderNo;

}
