package com.nhnacademy.eggplantdelivery.module;

import static com.nhnacademy.eggplantdelivery.constant.ExchangeConstant.DIRECT_EXCHANGE;
import static com.nhnacademy.eggplantdelivery.constant.RoutingKeyConstant.ROUTING_REQUEST_TRACKING_NO;
import static com.nhnacademy.eggplantdelivery.constant.RoutingKeyConstant.ROUTING_RESPONSE_TRACKING_NO;

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

    private final RabbitTemplate rabbitTemplate;

    public void send(@Validated final OrderInfoRequestDto orderInfoRequestDto) {
        rabbitTemplate.convertAndSend(DIRECT_EXCHANGE.getValue(), ROUTING_REQUEST_TRACKING_NO.getValue(), orderInfoRequestDto);
    }

    public void sendTrackingNo(@Validated final OrderInfoRequestDto orderInfoRequestDto) {
        rabbitTemplate.convertAndSend(DIRECT_EXCHANGE.getValue(), ROUTING_RESPONSE_TRACKING_NO.getValue(), orderInfoRequestDto);
    }

}
