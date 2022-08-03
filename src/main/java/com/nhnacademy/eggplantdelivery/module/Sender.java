package com.nhnacademy.eggplantdelivery.module;

import com.nhnacademy.eggplantdelivery.dto.request.OrderInfoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * RabbitMQ 에 MessageQueue 에 데이터를 전송하는 클래스 입니다.
 *
 * @author 김훈민, 조재철
 * @version 1.0.0
 */
@RequiredArgsConstructor
@Component
public class Sender {

    private final RabbitTemplate rabbitTemplate;

    public void send(final OrderInfoRequestDto orderInfoRequestDto) {
        rabbitTemplate.convertAndSend("exchange.direct", "routing.Eggplant", orderInfoRequestDto);
    }

    public void sendTrackingNo(final OrderInfoRequestDto orderInfoRequestDto) {
        rabbitTemplate.convertAndSend("exchange.direct", "routing.TrackingNo", orderInfoRequestDto);
    }

}
