package com.nhnacademy.eggplantdelivery.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : 조재철
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public class CreatedTrackingNoDto {

    private String trackingNo;

    private String orderNo;

}
