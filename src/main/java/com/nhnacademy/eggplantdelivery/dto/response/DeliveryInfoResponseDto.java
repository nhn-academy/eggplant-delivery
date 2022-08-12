package com.nhnacademy.eggplantdelivery.dto.response;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
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

    @NotBlank(message = "수취인 이름은 필수 입니다.")
    private String receiverName;

    @NotBlank(message = "수취인 주소는 필수 입니다.")
    private String receiverAddress;

    @NotBlank(message = "수취인 휴대번호는 필수 입니다.")
    private String receiverPhone;

}
