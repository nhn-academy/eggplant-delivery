package com.nhnacademy.eggplantdelivery.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.eggplantdelivery.adaptor.DeliveryAdaptor;
import com.nhnacademy.eggplantdelivery.dto.request.OrderInfoRequestDto;
import com.nhnacademy.eggplantdelivery.entity.DeliveryInfo;
import com.nhnacademy.eggplantdelivery.entity.status.Status;
import com.nhnacademy.eggplantdelivery.module.Sender;
import com.nhnacademy.eggplantdelivery.module.UuidGenerator;
import com.nhnacademy.eggplantdelivery.repository.DeliveryInfoRepository;
import com.nhnacademy.eggplantdelivery.service.DeliveryService;
import java.util.UUID;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
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
    Sender sender;

    @MockBean
    DeliveryAdaptor deliveryAdaptor;

    @Autowired
    DeliveryService service;

    private OrderInfoRequestDto orderInfoRequestDto;

    @BeforeEach
    void beforeSetting() {
        // given
        orderInfoRequestDto = new OrderInfoRequestDto(
            null, "1", "1", "1", "1", "1", 1
        );
    }

    @Test
    void testCreateTrackingNo() {
        String nameSpace = RandomStringUtils.random(32, true, true);

        UUID trackingNo = UuidGenerator.generateType5Uuid(nameSpace,
            orderInfoRequestDto.getShopHost() + orderInfoRequestDto.getOrderNo());

        DeliveryInfo deliveryInfo = DeliveryInfo.builder()
                                                .trackingNo(trackingNo.toString())
                                                .status(Status.DELIVERING)
                                                .receiverName(orderInfoRequestDto.getReceiverName())
                                                .receiverAddress(orderInfoRequestDto.getReceiverAddress())
                                                .receiverPhone(orderInfoRequestDto.getReceiverPhone())
                                                .orderNo(orderInfoRequestDto.getOrderNo())
                                                .build();

        when(deliveryInfoRepository.save(deliveryInfo)).thenReturn(any());

        // when
        service.createTrackingNo(orderInfoRequestDto);

        // then
        verify(deliveryInfoRepository).save(any());
    }

    @Test
    void sendTrackingNo() {
        doNothing().when(deliveryAdaptor).sendTrackingNo(orderInfoRequestDto);

        service.sendTrackingNo(orderInfoRequestDto);

        verify(deliveryAdaptor).sendTrackingNo(orderInfoRequestDto);
    }
}