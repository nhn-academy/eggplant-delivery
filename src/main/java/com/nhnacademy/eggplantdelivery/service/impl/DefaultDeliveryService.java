package com.nhnacademy.eggplantdelivery.service.impl;

import com.nhnacademy.eggplantdelivery.dto.request.OrderInfoDto;
import com.nhnacademy.eggplantdelivery.entity.DeliveryInfo;
import com.nhnacademy.eggplantdelivery.entity.status.Status;
import com.nhnacademy.eggplantdelivery.mapper.impl.DeliveryInfoMapper;
import com.nhnacademy.eggplantdelivery.module.Receiver;
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

    private final Sender sender;
    private final Receiver receiver;
    private final DeliveryInfoRepository deliveryInfoRepository;
    private final DeliveryInfoMapper deliveryInfoMapper;

    @Override
    public Long createTrackingNo(final OrderInfoDto orderInfoDto) {
        sender.send(orderInfoDto);

        OrderInfoDto orderInfo = receiver.receive();

        Long trackingNo = Long.parseLong(RandomStringUtils.random(16, false, true));

        DeliveryInfo deliveryInfo = deliveryInfoMapper.toEntity(orderInfo);
        deliveryInfo.setTrackingNo(trackingNo);
        deliveryInfo.setStatus(Status.DELIVERING);

        deliveryInfoRepository.save(deliveryInfo);

        return deliveryInfo.getTrackingNo();
    }

}
