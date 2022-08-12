package com.nhnacademy.eggplantdelivery.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 만들어진 운송장 번호를 담은 Dto 입니다.
 *
 * @author : 김훈민, 조재철
 * @version 1.0.0
 */
@Getter
@AllArgsConstructor
public class CreatedTrackingNoDto {

    @NotBlank(message = "운송장 번호가 유효하지 않습니다.")
    private String trackingNo;

    @NotBlank(message = "주문 번호가 유효하지 않습니다.")
    private String orderNo;

}
