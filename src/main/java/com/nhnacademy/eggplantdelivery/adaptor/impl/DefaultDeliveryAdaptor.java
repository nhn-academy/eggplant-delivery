package com.nhnacademy.eggplantdelivery.adaptor.impl;

import com.nhnacademy.eggplantdelivery.adaptor.DeliveryAdaptor;
import com.nhnacademy.eggplantdelivery.dto.request.OrderInfoRequestDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 배송 요청을 한 서버에 응답을 하는 클래스 입니다.
 *
 * @version 1.0.0
 */
@Component
public class DefaultDeliveryAdaptor implements DeliveryAdaptor {

    @Override
    public void sendTrackingNo(final OrderInfoRequestDto orderInfoRequestDto) {
        WebClient webClient = WebClient.builder()
                                       .baseUrl(orderInfoRequestDto.getShopHost())
                                       .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                       .build();

        webClient.post()
                 .uri("/delivery/tracking-no")
                 .bodyValue(orderInfoRequestDto.getTrackingNo())
                 .retrieve()
                 .toEntity(Void.class)
                 .block();
    }
}
