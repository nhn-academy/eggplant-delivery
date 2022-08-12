package com.nhnacademy.eggplantdelivery.module;

import com.nhnacademy.eggplantdelivery.dto.request.DeliveryStatusUpdateRequestDto;
import com.nhnacademy.eggplantdelivery.dto.request.OrderInfoRequestDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * RabbitMQ 에 MessageQueue 에 데이터를 전송하는 클래스 입니다.
 *
 * @author 김훈민, 조재철
 * @version 1.0.0
 */
@RequiredArgsConstructor
@Component
public class Sender {

    private static final String DELIVERY_EXCHANGE = "exchange.direct";
    private final RabbitTemplate rabbitTemplate;

    public void send(@Validated final OrderInfoRequestDto orderInfoRequestDto) {
        rabbitTemplate.convertAndSend(DELIVERY_EXCHANGE, "routing.Eggplant", orderInfoRequestDto);
    }

    public void sendTrackingNo(@Validated final OrderInfoRequestDto orderInfoRequestDto) {
        rabbitTemplate.convertAndSend(DELIVERY_EXCHANGE, "routing.TrackingNo", orderInfoRequestDto);
    }

    /**
     * 파라미터로 전달된 배송 상태 수정 객체를 Queue 에 담기 위한 메소드 입니다.
     *
     * @param deliveryStatusUpdateRequestDto 배송 상태, 운송장 번호를 담은 요청 Dto 입니다.
     */
    public void sendUpdateStatus(@Validated final List<DeliveryStatusUpdateRequestDto> deliveryStatusUpdateRequestDto) {
        for (DeliveryStatusUpdateRequestDto statusUpdateRequestDto : deliveryStatusUpdateRequestDto) {
            rabbitTemplate.convertAndSend(DELIVERY_EXCHANGE, "routing.UpdateStatus",
                statusUpdateRequestDto);
        }
    }

}
