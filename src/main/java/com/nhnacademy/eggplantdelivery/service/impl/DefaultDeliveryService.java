package com.nhnacademy.eggplantdelivery.service.impl;

import com.nhnacademy.eggplantdelivery.adaptor.DeliveryAdaptor;
import com.nhnacademy.eggplantdelivery.dto.request.DeliveryInfoStatusRequestDto;
import com.nhnacademy.eggplantdelivery.dto.request.DeliveryStatusUpdateRequestDto;
import com.nhnacademy.eggplantdelivery.dto.request.OrderInfoRequestDto;
import com.nhnacademy.eggplantdelivery.dto.response.DeliveryInfoStatusResponseDto;
import com.nhnacademy.eggplantdelivery.entity.DeliveryInfo;
import com.nhnacademy.eggplantdelivery.entity.status.Status;
import com.nhnacademy.eggplantdelivery.exception.DeliveryInfoNotFoundException;
import com.nhnacademy.eggplantdelivery.module.Sender;
import com.nhnacademy.eggplantdelivery.module.UuidGenerator;
import com.nhnacademy.eggplantdelivery.repository.DeliveryInfoRepository;
import com.nhnacademy.eggplantdelivery.service.DeliveryService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
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
    private final Sender sender;
    private final DeliveryAdaptor adaptor;

    @Transactional
    @Override
    public void createTrackingNo(final OrderInfoRequestDto orderInfoRequestDto) {
        UUID trackingNo = UuidGenerator.generateType5Uuid(RandomStringUtils.random(32, true, false),
            orderInfoRequestDto.getOrderNo());

        deliveryInfoRepository.save(DeliveryInfo.builder()
                                                .trackingNo(trackingNo.toString())
                                                .status(Status.READY)
                                                .receiverName(orderInfoRequestDto.getReceiverName())
                                                .receiverAddress(orderInfoRequestDto.getReceiverAddress())
                                                .receiverPhone(orderInfoRequestDto.getReceiverPhone())
                                                .orderNo(orderInfoRequestDto.getOrderNo())
                                                .build());

        orderInfoRequestDto.insertTrackingNo(trackingNo);
        sender.sendTrackingNo(orderInfoRequestDto);
    }

    @Override
    public void sendTrackingNo(final OrderInfoRequestDto orderInfoRequestDto) {
        adaptor.sendTrackingNo(orderInfoRequestDto);
    }

    @Transactional
    @Override
    public void sendUpdateStatus(final DeliveryStatusUpdateRequestDto deliveryStatusUpdateRequestDto) {
        DeliveryInfo deliveryInfo = deliveryInfoRepository.findById(deliveryStatusUpdateRequestDto.getTrackingNo())
                                                          .orElseThrow(DeliveryInfoNotFoundException::new);

        deliveryInfo.updateStatus(deliveryStatusUpdateRequestDto.getStatus());

        adaptor.sendUpdateStatus(DeliveryInfoStatusRequestDto.builder()
                                                             .trackingNo(deliveryInfo.getTrackingNo())
                                                             .status(deliveryInfo.getStatus())
                                                             .shopHost(deliveryInfo.getShopHost())
                                                             .build());
    }

    @Override
    public List<DeliveryInfoStatusResponseDto> retrieveDeliveryStatus() {
        return deliveryInfoRepository.retrieveDeliveryStatus();
    }

}
