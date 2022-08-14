package com.nhnacademy.eggplantdelivery.repository;

import com.nhnacademy.eggplantdelivery.dto.response.DeliveryLocationResponseDto;
import com.nhnacademy.eggplantdelivery.entity.DeliveryInfo;
import com.nhnacademy.eggplantdelivery.entity.QDeliveryInfo;
import com.nhnacademy.eggplantdelivery.entity.QLocation;
import com.querydsl.core.types.Projections;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

/**
 * 배송정보 Db 를 참조하는 QueryDSL Repository 입니다.
 *
 * @author 김훈민, 조재철
 * @version 1.0.0
 */
public class DeliveryInfoRepositoryImpl extends QuerydslRepositorySupport implements CustomDeliveryInfoRepository {
    QDeliveryInfo deliveryInfo = QDeliveryInfo.deliveryInfo;
    QLocation location = QLocation.location;

    public DeliveryInfoRepositoryImpl() {
        super(DeliveryInfo.class);
    }

    @Override
    public List<DeliveryLocationResponseDto> retrieveDeliveryLocationResponseDto(String trackingNo) {
        return from(deliveryInfo)
            .innerJoin(location)
            .on(deliveryInfo.trackingNo.eq(location.pk.trackingNo))
            .where(deliveryInfo.trackingNo.eq(trackingNo))
            .select(Projections.constructor(DeliveryLocationResponseDto.class,
                deliveryInfo.status,
                deliveryInfo.completionTime,
                location.pk.locationNo,
                location.arrivalTime))
            .fetch();
    }

}
