package com.nhnacademy.eggplantdelivery.dto.response;

import com.nhnacademy.eggplantdelivery.entity.status.Status;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 배송 정보 수정을 위한 응답 Dto 입니다.
 *
 * @author 김훈민, 조재철
 * @version 1.0.0
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryInfoStatusResponseDto {

    @NotBlank(message = "운송장 번호가 유효하지 않습니다.")
    private String trackingNo;

    private Status status;

}
