package com.nhnacademy.eggplantdelivery.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 주문정보 요청 Dto 입니다.
 *
 * @version 1.0.0
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfoDto {

    private String receiverName;
    private String receiverAddress;
    private String receiverPhone;
    private String shopHost;
    private String orderNo;

}
