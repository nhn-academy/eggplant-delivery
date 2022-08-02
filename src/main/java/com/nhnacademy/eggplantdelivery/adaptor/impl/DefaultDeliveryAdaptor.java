package com.nhnacademy.eggplantdelivery.adaptor.impl;

import com.nhnacademy.eggplantdelivery.adaptor.DeliveryAdaptor;
import com.nhnacademy.eggplantdelivery.dto.request.OrderInfoRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 배송 요청을 한 서버에 응답을 하는 클래스 입니다.
 *
 * @version 1.0.0
 */
@Component
@Slf4j
public class DefaultDeliveryAdaptor implements DeliveryAdaptor {

    public static final String PROTOCOL = "http://";

    @Override
    public void sendTrackingNo(final OrderInfoRequestDto orderInfoRequestDto) {

        WebClient webClient = WebClient.builder()
                                       .baseUrl(PROTOCOL + orderInfoRequestDto.getShopHost() + ":"
                                           + orderInfoRequestDto.getShopPort())
                                       .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                       .build();


        // FIXME: https://github.com/netty/netty/issues/11020 해당 오류 M1 이슈로 평가되는데 현재 개발에는 큰 문제가 없어보임 차후에 해결 해야함.
        webClient.post()
                 .uri(uriBuilder -> uriBuilder.path("/web-client/delivery/tracking-no")
                                              .build())
                 .bodyValue(orderInfoRequestDto.getTrackingNo())
                 .exchangeToMono(clientResponse -> {
                     if (clientResponse.statusCode().equals(HttpStatus.OK)) {
                         return clientResponse.bodyToMono(ResponseEntity.class);
                     } else {
                         return null; // TODO : 에러 발생시 해결할 사항
                     }
                 })
                 .block();

    }

}
