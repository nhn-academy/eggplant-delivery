package com.nhnacademy.eggplantdelivery.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 주문정보 요청 Dto 입니다.
 *
 * @version 1.0.0
 */
@Getter
@Setter
public class OrderInfoRequestDto {

    private String receiverName;
    private String receiverAddress;
    private String receiverPhone;

}
