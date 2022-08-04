package com.nhnacademy.eggplantdelivery.dto.response;

import com.nhnacademy.eggplantdelivery.entity.status.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryInfoStatusResponseDto {

    private String trackingNo;
    private Status status;

}
