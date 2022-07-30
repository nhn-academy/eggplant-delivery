package com.nhnacademy.eggplantdelivery.service;

import com.nhnacademy.eggplantdelivery.dto.request.OrderInfoDto;

/**
 * 배송 요청과 Rabbit MQ 관련 로직을 처리하는 인터페이스 입니다.
 *
 * @version 1.0.0
 */
public interface DeliveryService {

    Long createTrackingNo(final OrderInfoDto orderInfoDto);

}
