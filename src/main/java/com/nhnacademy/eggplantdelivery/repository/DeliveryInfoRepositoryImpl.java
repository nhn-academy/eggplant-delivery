package com.nhnacademy.eggplantdelivery.repository;

import com.nhnacademy.eggplantdelivery.dto.response.DeliveryInfoStatusResponseDto;
import com.nhnacademy.eggplantdelivery.entity.DeliveryInfo;
import com.nhnacademy.eggplantdelivery.entity.QDeliveryInfo;
import com.nhnacademy.eggplantdelivery.entity.status.Status;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

/**
 * Query DSL 메서드의 구현체 클래스 입니다.
 *
 * @author 김훈민, 조재철
 * @version 1.0.0
 */
@Repository
public class DeliveryInfoRepositoryImpl extends QuerydslRepositorySupport implements CustomDeliveryRepository {

    public DeliveryInfoRepositoryImpl() {
        super(DeliveryInfo.class);
    }

    @Override
    public List<DeliveryInfoStatusResponseDto> retrieveDeliveryStatus() {
        QDeliveryInfo deliveryInfo = QDeliveryInfo.deliveryInfo;

        return from(deliveryInfo)
            .where(deliveryInfo.status.eq(Status.DELIVERING))
            .orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
            .limit(10L)
            .select(Projections.constructor(DeliveryInfoStatusResponseDto.class, deliveryInfo.trackingNo,
                deliveryInfo.status))
            .fetch();
    }

}