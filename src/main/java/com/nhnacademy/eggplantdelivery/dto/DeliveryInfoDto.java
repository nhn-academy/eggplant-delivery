package com.nhnacademy.eggplantdelivery.dto;

import com.nhnacademy.eggplantdelivery.entity.status.Status;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : 조재철
 * @since 1.0
 */

@Getter
@Setter
public class DeliveryInfoDto {

    private Long trackingNo;

    private Status status;

    private String receiverName;

    private String receiverAddress;

}
