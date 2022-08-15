package com.nhnacademy.eggplantdelivery.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.eggplantdelivery.adaptor.DeliveryAdaptor;
import com.nhnacademy.eggplantdelivery.dto.request.OrderInfoRequestDto;
import com.nhnacademy.eggplantdelivery.entity.DeliveryInfo;
import com.nhnacademy.eggplantdelivery.entity.status.Status;
import com.nhnacademy.eggplantdelivery.module.Sender;
import com.nhnacademy.eggplantdelivery.repository.DeliveryInfoRepository;
import com.nhnacademy.eggplantdelivery.repository.LocationRepository;
import com.nhnacademy.eggplantdelivery.service.DeliveryService;
import com.nhnacademy.eggplantdelivery.utill.AesGenerator;
import com.nhnacademy.eggplantdelivery.utill.UuidGenerator;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@Import(DefaultDeliveryService.class)
class DefaultDeliveryServiceTest {

    @MockBean
    DeliveryInfoRepository deliveryInfoRepository;

    @MockBean
    LocationRepository locationRepository;

    @MockBean
    Sender sender;

    @MockBean
    DeliveryAdaptor deliveryAdaptor;

    @MockBean
    AesGenerator aesGenerator;

    @Autowired
    DeliveryService service;

    private OrderInfoRequestDto orderInfoRequestDto;
    private DeliveryInfo deliveryInfo;

    @BeforeEach
    void beforeSetting() {
        orderInfoRequestDto = new OrderInfoRequestDto(
            null, "1", "1", "1", "1", "1", "1"
        );

        UUID uuid = UuidGenerator.ver5UuidFromNamespaceAndBytes(("Host" + "OrderNo" + LocalDateTime.now()).getBytes(
            StandardCharsets.UTF_8));

        deliveryInfo = DeliveryInfo.builder()
                                   .trackingNo(uuid.toString())
                                   .status(Status.DELIVERING)
                                   .receiverName(
                                       aesGenerator.aesEcbEncode(orderInfoRequestDto.getReceiverName()))
                                   .receiverAddress(
                                       aesGenerator.aesEcbEncode(orderInfoRequestDto.getReceiverAddress()))
                                   .receiverDetailAddress(aesGenerator.aesEcbEncode(
                                       orderInfoRequestDto.getReceiverDetailAddress()))
                                   .receiverPhone(
                                       aesGenerator.aesEcbEncode(orderInfoRequestDto.getReceiverPhone()))
                                   .orderNo(orderInfoRequestDto.getOrderNo())
                                   .shopHost(aesGenerator.aesEcbEncode(orderInfoRequestDto.getShopHost()))
                                   .build();
    }

    @Test
    @DisplayName("운송장 번호 생성 및 주문정보 저장")
    void testCreateTrackingNo() {
        when(deliveryInfoRepository.save(any(DeliveryInfo.class))).thenReturn(deliveryInfo);

        service.createTrackingNo(orderInfoRequestDto);

        verify(deliveryInfoRepository).save(any(DeliveryInfo.class));
    }

    @Test
    @DisplayName("운송장 번호 전송")
    void testSendTrackingNo() {
        doNothing().when(deliveryAdaptor).sendTrackingNo(orderInfoRequestDto);

        service.sendTrackingNo(orderInfoRequestDto);

        verify(deliveryAdaptor).sendTrackingNo(orderInfoRequestDto);
    }

    @Test
    @DisplayName("배송정보, 배송위치 정보 조회")
    void testRetrieveDeliveryLocation() {
        when(deliveryInfoRepository.retrieveDeliveryLocationResponseDto(anyString())).thenReturn(Collections.emptyList());

        service.retrieveDeliveryLocation(deliveryInfo.getTrackingNo());

        verify(deliveryInfoRepository, times(1)).retrieveDeliveryLocationResponseDto(anyString());
    }

}
