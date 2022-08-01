package com.nhnacademy.eggplantdelivery.module;

import com.nhnacademy.eggplantdelivery.dto.request.OrderInfoDto;
import com.nhnacademy.eggplantdelivery.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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

    private final DeliveryService deliveryService;

    @RabbitListener(bindings = @QueueBinding(
        exchange = @Exchange(name = "exchange.direct", type = ExchangeTypes.DIRECT),
        value = @Queue(name = "queue.Eggplant"),
        key = "routing.Eggplant")
    )
    public void receive(OrderInfoDto orderInfoDto) {
        deliveryService.createTrackingNo(orderInfoDto);
    }

}
