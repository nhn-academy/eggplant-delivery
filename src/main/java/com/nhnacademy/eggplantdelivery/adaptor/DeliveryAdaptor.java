package com.nhnacademy.eggplantdelivery.adaptor;

import com.nhnacademy.eggplantdelivery.dto.request.DeliveryInfoStatusRequestDto;
import com.nhnacademy.eggplantdelivery.dto.request.OrderInfoRequestDto;
import org.springframework.validation.annotation.Validated;

/**
 * 배송 요청을 한 서버에 응답을 하는 인터페이스 입니다.
 *
 * @author 김훈민, 조재철
 * @version 1.0.0
 */
public interface DeliveryAdaptor {

    void sendTrackingNo(@Validated final OrderInfoRequestDto orderInfoRequestDto);

    void sendUpdateStatus(@Validated final DeliveryInfoStatusRequestDto deliveryStatusUpdateRequestDto);

}
