package com.nhnacademy.eggplantdelivery.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.eggplantdelivery.adaptor.DeliveryAdaptor;
import com.nhnacademy.eggplantdelivery.dto.request.DeliveryStatusUpdateRequestDto;
import com.nhnacademy.eggplantdelivery.dto.request.OrderInfoRequestDto;
import com.nhnacademy.eggplantdelivery.entity.DeliveryInfo;
import com.nhnacademy.eggplantdelivery.entity.status.Status;
import com.nhnacademy.eggplantdelivery.module.Sender;
import com.nhnacademy.eggplantdelivery.repository.DeliveryInfoRepository;
import com.nhnacademy.eggplantdelivery.service.DeliveryService;
import com.nhnacademy.eggplantdelivery.utill.UuidGenerator;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

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
            null, "1", "1", "1", "1", "1"
        );
    }

    @Test
    void testCreateTrackingNo() {
        UUID uuid = UuidGenerator.ver5UuidFromNamespaceAndBytes(("Host" + "OrderNo").getBytes(
            StandardCharsets.UTF_8));

        DeliveryInfo deliveryInfo = DeliveryInfo.builder()
                                                .trackingNo(uuid.toString())
                                                .status(Status.DELIVERING)
                                                .receiverName(orderInfoRequestDto.getReceiverName())
                                                .receiverAddress(orderInfoRequestDto.getReceiverAddress())
                                                .receiverPhone(orderInfoRequestDto.getReceiverPhone())
                                                .orderNo(orderInfoRequestDto.getOrderNo())
                                                .shopHost(orderInfoRequestDto.getShopHost())
                                                .build();

        when(deliveryInfoRepository.save(deliveryInfo)).thenReturn(any());

        // when
        service.createTrackingNo(orderInfoRequestDto);

        // then
        verify(deliveryInfoRepository).save(any());
    }

    @Test
    void testSendTrackingNo() {
        doNothing().when(deliveryAdaptor).sendTrackingNo(orderInfoRequestDto);

        service.sendTrackingNo(orderInfoRequestDto);

        verify(deliveryAdaptor).sendTrackingNo(orderInfoRequestDto);
    }

    @Test
    void testSendUpdateStatus() {
        UUID uuid = UuidGenerator.ver5UuidFromNamespaceAndBytes(("Host" + "OrderNo").getBytes(
            StandardCharsets.UTF_8));

        DeliveryInfo deliveryInfo = DeliveryInfo.builder()
                                                .trackingNo(uuid.toString())
                                                .status(Status.DELIVERING)
                                                .receiverName(orderInfoRequestDto.getReceiverName())
                                                .receiverAddress(orderInfoRequestDto.getReceiverAddress())
                                                .receiverPhone(orderInfoRequestDto.getReceiverPhone())
                                                .orderNo(orderInfoRequestDto.getOrderNo())
                                                .shopHost(orderInfoRequestDto.getShopHost())
                                                .build();

        DeliveryStatusUpdateRequestDto deliveryStatusUpdateRequestDto = new DeliveryStatusUpdateRequestDto();

        ReflectionTestUtils.setField(deliveryStatusUpdateRequestDto, "trackingNo", uuid.toString());
        ReflectionTestUtils.setField(deliveryStatusUpdateRequestDto, "status", Status.DELIVERING);

        when(deliveryInfoRepository.findById(anyString())).thenReturn(Optional.ofNullable(deliveryInfo));

        Objects.requireNonNull(deliveryInfo).updateStatus(deliveryStatusUpdateRequestDto.getStatus());

        doNothing().when(deliveryAdaptor).sendUpdateStatus(any());

        service.sendUpdateStatus(deliveryStatusUpdateRequestDto);

        verify(deliveryAdaptor).sendUpdateStatus(any());

    }

}
