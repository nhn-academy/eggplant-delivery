package com.nhnacademy.eggplantdelivery.service.impl;

import com.nhnacademy.eggplantdelivery.adaptor.DeliveryAdaptor;
import com.nhnacademy.eggplantdelivery.dto.request.OrderInfoRequestDto;
import com.nhnacademy.eggplantdelivery.entity.DeliveryInfo;
import com.nhnacademy.eggplantdelivery.entity.status.Status;
import com.nhnacademy.eggplantdelivery.module.Sender;
import com.nhnacademy.eggplantdelivery.repository.DeliveryInfoRepository;
import com.nhnacademy.eggplantdelivery.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;

/**
 * 배송 요청과 Rabbit MQ 관련 로직을 처리하는 클래스 입니다.
 *
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class DefaultDeliveryService implements DeliveryService {

    private final DeliveryInfoRepository deliveryInfoRepository;
    private final Sender sender;
    private final DeliveryAdaptor adaptor;

    @Override
    public void createTrackingNo(final OrderInfoRequestDto orderInfoRequestDto) {
        Long trackingNo = Long.parseLong(RandomStringUtils.random(16, false, true));

        deliveryInfoRepository.save(DeliveryInfo.builder()
                                                .trackingNo(trackingNo)
                                                .status(Status.DELIVERING)
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

}
