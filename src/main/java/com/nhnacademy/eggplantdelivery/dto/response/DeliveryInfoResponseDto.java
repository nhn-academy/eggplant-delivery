package com.nhnacademy.eggplantdelivery.dto.response;

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
public class DeliveryInfoResponseDto {

    private String receiverName;
    private String receiverAddress;
    private String receiverPhone;

}
