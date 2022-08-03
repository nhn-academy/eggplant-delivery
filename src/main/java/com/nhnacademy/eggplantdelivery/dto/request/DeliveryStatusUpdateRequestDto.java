package com.nhnacademy.eggplantdelivery.dto.request;

import com.nhnacademy.eggplantdelivery.entity.status.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeliveryStatusUpdateRequestDto {

    private String trackingNo;
    private Status status;

}
