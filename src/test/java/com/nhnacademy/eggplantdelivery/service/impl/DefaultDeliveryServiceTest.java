package com.nhnacademy.eggplantdelivery.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.eggplantdelivery.adaptor.DeliveryAdaptor;
import com.nhnacademy.eggplantdelivery.dto.request.DeliveryStatusUpdateRequestDto;
import com.nhnacademy.eggplantdelivery.dto.request.OrderInfoRequestDto;
import com.nhnacademy.eggplantdelivery.dto.response.DeliveryInfoStatusResponseDto;
import com.nhnacademy.eggplantdelivery.entity.DeliveryInfo;
import com.nhnacademy.eggplantdelivery.entity.status.Status;
import com.nhnacademy.eggplantdelivery.exception.DeliveryInfoNotFoundException;
import com.nhnacademy.eggplantdelivery.module.Sender;
import com.nhnacademy.eggplantdelivery.repository.DeliveryInfoRepository;
import com.nhnacademy.eggplantdelivery.service.DeliveryService;
import com.nhnacademy.eggplantdelivery.utill.UuidVer5Generator;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("운송장 번호 만들기")
    void testCreateTrackingNo() {
        UUID uuid = UuidVer5Generator.ver5UuidFromNamespaceAndBytes(("Host" + "OrderNo").getBytes(
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
    @DisplayName("만들어진 운송장 번호 전송")
    void testSendTrackingNo() {
        UUID uuid = UuidVer5Generator.ver5UuidFromNamespaceAndBytes(("Host" + "OrderNo").getBytes(
            StandardCharsets.UTF_8));

        OrderInfoRequestDto infoRequestDto = new OrderInfoRequestDto(uuid, orderInfoRequestDto.getReceiverName(),
            orderInfoRequestDto.getReceiverAddress(),
            orderInfoRequestDto.getReceiverPhone(), orderInfoRequestDto.getOrderNo(),
            orderInfoRequestDto.getShopHost());

        doNothing().when(deliveryAdaptor).sendTrackingNo(infoRequestDto);

        service.sendTrackingNo(infoRequestDto);

        verify(deliveryAdaptor).sendTrackingNo(infoRequestDto);
    }

    @Test
    @DisplayName("배송 상태 수정 후 전송")
    void testSendUpdateStatus() {
        UUID uuid = UuidVer5Generator.ver5UuidFromNamespaceAndBytes(("Host" + "OrderNo").getBytes(
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

    @Test
    @DisplayName("배송 상태 확인후 전송")
    void testRetrieveDeliveryStatus() {
        List<DeliveryInfoStatusResponseDto> deliveryInfoStatusResponseDtos =
            List.of(new DeliveryInfoStatusResponseDto());
        when(deliveryInfoRepository.retrieveDeliveryStatus()).thenReturn(deliveryInfoStatusResponseDtos);

        List<DeliveryInfoStatusResponseDto> deliveryInfoStatusResponseDtoList = service.retrieveDeliveryStatus();

        assertThat(deliveryInfoStatusResponseDtoList).isEqualTo(deliveryInfoStatusResponseDtos);
        verify(deliveryInfoRepository).retrieveDeliveryStatus();
    }

    @Test
    @DisplayName("배송정보 찾기 실패 예외처리")
    void testSendUpdateStatusThrownByDeliveryInfoNotFoundException() {
        DeliveryStatusUpdateRequestDto requestDto = new DeliveryStatusUpdateRequestDto("Un Known Id", Status.READY);

        when(deliveryInfoRepository.findById(any())).thenThrow(new DeliveryInfoNotFoundException());

        assertThatThrownBy(() ->
            service.sendUpdateStatus(requestDto)
        ).isInstanceOf(DeliveryInfoNotFoundException.class)
         .hasMessageContaining("해당 배송정보는 없습니다.");
    }

}
