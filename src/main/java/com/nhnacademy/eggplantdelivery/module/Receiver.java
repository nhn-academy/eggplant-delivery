package com.nhnacademy.eggplantdelivery.module;

import com.nhnacademy.eggplantdelivery.dto.request.OrderInfoRequestDto;
import com.nhnacademy.eggplantdelivery.dto.response.DeliveryInfoStatusResponseDto;
import com.nhnacademy.eggplantdelivery.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * RabbitMQ 에 MessageQueue 에서 데이터를 받는 클래스 입니다.
 *
 * @author 김훈민, 조재철
 * @version 1.0.0
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class Receiver {

    private final DeliveryService deliveryService;

    @RabbitListener(queues = "queue.RequestTrackingNo")
    public void receiveRequestTrackingNo(@Validated final OrderInfoRequestDto orderInfoRequestDto) {
        deliveryService.createTrackingNo(orderInfoRequestDto);
    }

    @RabbitListener(queues = "queue.ResponseTrackingNo")
    public void receiveResponseTrackingNo(@Validated final OrderInfoRequestDto orderInfoRequestDto) {
        deliveryService.sendTrackingNo(orderInfoRequestDto);
    }

}
