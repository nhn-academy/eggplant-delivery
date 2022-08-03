package com.nhnacademy.eggplantdelivery.dto.request;

import com.nhnacademy.eggplantdelivery.entity.status.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryInfoStatusRequestDto {

    private String trackingNo;
    private Status status;
    private String shopHost;

}
