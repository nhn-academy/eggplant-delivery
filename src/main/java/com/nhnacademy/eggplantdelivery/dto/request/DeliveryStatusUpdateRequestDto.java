package com.nhnacademy.eggplantdelivery.dto.request;

import com.nhnacademy.eggplantdelivery.entity.status.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 배송상태 수정을 위한 요청 Dto 입니다.
 *
 * @author 김훈민, 조재철
 * @version 1.0.0
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryStatusUpdateRequestDto {

    private String trackingNo;
    private Status status;

}
