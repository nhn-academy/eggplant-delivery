package com.nhnacademy.eggplantdelivery.service.impl;

import com.nhnacademy.eggplantdelivery.adaptor.DeliveryAdaptor;
import com.nhnacademy.eggplantdelivery.dto.request.CreatedTrackingNoDto;
import com.nhnacademy.eggplantdelivery.dto.request.DeliveryInfoStatusRequestDto;
import com.nhnacademy.eggplantdelivery.dto.request.OrderInfoRequestDto;
import com.nhnacademy.eggplantdelivery.dto.response.DeliveryInfoStatusResponseDto;
import com.nhnacademy.eggplantdelivery.dto.response.DeliveryLocationResponseDto;
import com.nhnacademy.eggplantdelivery.entity.DeliveryInfo;
import com.nhnacademy.eggplantdelivery.entity.status.Status;
import com.nhnacademy.eggplantdelivery.repository.DeliveryInfoRepository;
import com.nhnacademy.eggplantdelivery.service.DeliveryService;
import com.nhnacademy.eggplantdelivery.utill.AesGenerator;
import com.nhnacademy.eggplantdelivery.utill.UuidGenerator;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 배송 요청과 Rabbit MQ 관련 로직을 처리하는 클래스 입니다.
 *
 * @author 김훈민, 조재철
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultDeliveryService implements DeliveryService {

    private final DeliveryInfoRepository deliveryInfoRepository;
    private final DeliveryAdaptor adaptor;
    private final AesGenerator aesGenerator;

    @Transactional
    @Override
    public void createTrackingNo(final OrderInfoRequestDto orderInfoRequestDto) {
        UUID trackingNo = UuidGenerator.ver5UuidFromNamespaceAndBytes(
            (orderInfoRequestDto.getSuccessHost() + orderInfoRequestDto.getOrderNo() + LocalDateTime.now()).getBytes(
                StandardCharsets.UTF_8));

        deliveryInfoRepository.save(DeliveryInfo.builder()
                                                .trackingNo(trackingNo.toString())
                                                .status(Status.READY)
                                                .orderNo(orderInfoRequestDto.getOrderNo())
                                                .receiverName(
                                                    aesGenerator.aesEcbEncode(orderInfoRequestDto.getReceiverName()))
                                                .receiverAddress(
                                                    aesGenerator.aesEcbEncode(orderInfoRequestDto.getReceiverAddress()))
                                                .receiverDetailAddress(
                                                    aesGenerator.aesEcbEncode(
                                                        orderInfoRequestDto.getReceiverDetailAddress()))
                                                .receiverPhone(
                                                    aesGenerator.aesEcbEncode(orderInfoRequestDto.getReceiverPhone()))
                                                .shopHost(
                                                    aesGenerator.aesEcbEncode(orderInfoRequestDto.getSuccessHost()))
                                                .build());

        CreatedTrackingNoDto createdTrackingNoDto =
            new CreatedTrackingNoDto(trackingNo.toString(),
                orderInfoRequestDto.getOrderNo());

        adaptor.sendTrackingNo(createdTrackingNoDto, orderInfoRequestDto.getSuccessHost());
    }

    @Override
    public List<DeliveryLocationResponseDto> retrieveDeliveryLocation(final String trackingNo) {
        return deliveryInfoRepository.retrieveDeliveryLocationResponseDto(trackingNo);
    }

    @Override
    public void transmitDeliveryStatus(DeliveryInfoStatusResponseDto deliveryInfoStatusResponseDto) {
        DeliveryInfoStatusRequestDto deliveryInfoStatusRequestDto
            = DeliveryInfoStatusRequestDto.builder()
                                          .orderNo(deliveryInfoStatusResponseDto.getOrderNo())
                                          .status(deliveryInfoStatusResponseDto.getStatus().toString())
                                          .arrivalTime(deliveryInfoStatusResponseDto.getArrivalTime())
                                          .build();

        String shopHost = aesGenerator.aesEcbDecode(deliveryInfoStatusResponseDto.getShopHost());

        adaptor.sendChangeDeliveryStatus(deliveryInfoStatusRequestDto, shopHost);
    }

    @Override
    public void createTrackingNoDlx(OrderInfoRequestDto orderInfoRequestDto) {
        adaptor.sendTrackingNoDlxToLogServer(orderInfoRequestDto);
    }

    @Override
    public void transmitDeliveryStatusDlx(DeliveryInfoStatusResponseDto deliveryInfoStatusResponseDto) {
        adaptor.sendDeliveryStatusDlxToLogServer(deliveryInfoStatusResponseDto);
    }

}
