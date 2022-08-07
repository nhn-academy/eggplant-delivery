package com.nhnacademy.eggplantdelivery.dto.request;

import com.nhnacademy.eggplantdelivery.entity.status.Status;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 수정된 배송정보 전송을 위한 Dto 입니다.
 *
 * @author 김훈민, 조재철
 * @version 1.0.0
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryInfoStatusRequestDto {

    @NotBlank(message = "운송장 번호가 유효하지 않습니다.")
    private String trackingNo;

    private Status status;

    @NotBlank(message = "해당 쇼핑몰 host 주소는 필수 입니다.")
    private String shopHost;

}
