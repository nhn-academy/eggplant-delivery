package com.nhnacademy.eggplantdelivery.adaptor.impl;

import com.nhnacademy.eggplantdelivery.adaptor.DeliveryAdaptor;
import com.nhnacademy.eggplantdelivery.dto.request.CreatedTrackingNoDto;
import com.nhnacademy.eggplantdelivery.dto.request.DeliveryInfoStatusRequestDto;
import com.nhnacademy.eggplantdelivery.dto.response.DeliveryInfoStatusResponseDto;
import com.nhnacademy.eggplantdelivery.utill.AesGenerator;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 배송 요청을 한 서버에 응답을 하는 클래스 입니다.
 *
 * @author 김훈민, 조재철
 * @version 1.0.0
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DefaultDeliveryAdaptor implements DeliveryAdaptor {

    private final AesGenerator aesGenerator;
    public static final String PROTOCOL = "http://";

    @Override
    public void sendTrackingNo(final CreatedTrackingNoDto createdTrackingNoDto, String shopHost) {

        WebClient webClient = WebClient.builder()
                                       .baseUrl(PROTOCOL + shopHost
                                           + ":7072")
                                       .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                       .build();

        webClient.post()
                 .uri(uriBuilder -> uriBuilder.path("/eggplant/tracking-no")
                                              .build())
                 .bodyValue(createdTrackingNoDto)
                 .exchangeToMono(clientResponse -> {
                     if (clientResponse.statusCode().equals(HttpStatus.OK)) {
                         return clientResponse.bodyToMono(ResponseEntity.class);
                     } else {
                         throw new AmqpRejectAndDontRequeueException("운송장 번호 전달의 과정에서 통신 문제가 발생하였습니다.");
                     }
                 })
                 .block();
    }

    @Override
    public void sendChangeDeliveryStatus(final DeliveryInfoStatusRequestDto deliveryInfoStatusRequestDto, String shopHost) {
        WebClient webClient = WebClient.builder()
                                       .baseUrl(PROTOCOL + aesGenerator.aesEcbDecode(
                                           shopHost) + ":7072")
                                       .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                       .build();

        webClient.patch()
                 .uri(uriBuilder -> uriBuilder.path("/eggplant/delivery-info")
                                              .build())
                 .bodyValue(deliveryInfoStatusRequestDto)
                 .exchangeToMono(clientResponse -> {
                     if (clientResponse.statusCode().equals(HttpStatus.OK)) {
                         return clientResponse.bodyToMono(ResponseEntity.class);
                     } else {
                         return null;
                     }
                 })
                 .block();
    }

}
