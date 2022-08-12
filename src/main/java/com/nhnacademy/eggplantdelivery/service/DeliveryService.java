package com.nhnacademy.eggplantdelivery.service;

import com.nhnacademy.eggplantdelivery.dto.request.DeliveryStatusUpdateRequestDto;
import com.nhnacademy.eggplantdelivery.dto.request.OrderInfoRequestDto;

/**
 * 배송 요청과 Rabbit MQ 관련 로직을 처리하는 인터페이스 입니다.
 *
 * @author 김훈민, 조재철
 * @version 1.0.0
 */
public interface DeliveryService {

    void createTrackingNo(final OrderInfoRequestDto orderInfoRequestDto);

    void sendTrackingNo(final OrderInfoRequestDto orderInfoRequestDto);

    void sendUpdateStatus(final DeliveryStatusUpdateRequestDto deliveryStatusUpdateRequestDto);

}
