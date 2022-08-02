package com.nhnacademy.eggplantdelivery.adaptor;

import com.nhnacademy.eggplantdelivery.dto.request.OrderInfoRequestDto;

/**
 * 배송 요청을 한 서버에 응답을 하는 인터페이스 입니다.
 *
 * @version 1.0.0
 */
public interface DeliveryAdaptor {
    void sendTrackingNo(final OrderInfoRequestDto orderInfoRequestDto);

}
