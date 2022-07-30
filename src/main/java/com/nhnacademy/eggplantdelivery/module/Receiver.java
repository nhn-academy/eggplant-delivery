package com.nhnacademy.eggplantdelivery.module;

import com.nhnacademy.eggplantdelivery.dto.request.OrderInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * RabbitMQ 에 MessageQueue 에서 데이터를 받는 클래스 입니다.
 *
 * @version 1.0.0
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class Receiver {

    private final RabbitTemplate rabbitTemplate;

    public OrderInfoDto receive() {
        return (OrderInfoDto) rabbitTemplate.receiveAndConvert("queue.Eggplant");
    }

}
