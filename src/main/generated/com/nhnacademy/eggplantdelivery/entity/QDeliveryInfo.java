package com.nhnacademy.eggplantdelivery.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDeliveryInfo is a Querydsl query type for DeliveryInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDeliveryInfo extends EntityPathBase<DeliveryInfo> {

    private static final long serialVersionUID = 708749794L;

    public static final QDeliveryInfo deliveryInfo = new QDeliveryInfo("deliveryInfo");

    public final DateTimePath<java.time.LocalDateTime> completionTime = createDateTime("completionTime", java.time.LocalDateTime.class);

    public final StringPath orderNo = createString("orderNo");

    public final StringPath receiverAddress = createString("receiverAddress");

    public final StringPath receiverName = createString("receiverName");

    public final StringPath receiverPhone = createString("receiverPhone");

    public final StringPath shopHost = createString("shopHost");

    public final EnumPath<com.nhnacademy.eggplantdelivery.entity.status.Status> status = createEnum("status", com.nhnacademy.eggplantdelivery.entity.status.Status.class);

    public final StringPath trackingNo = createString("trackingNo");

    public QDeliveryInfo(String variable) {
        super(DeliveryInfo.class, forVariable(variable));
    }

    public QDeliveryInfo(Path<? extends DeliveryInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeliveryInfo(PathMetadata metadata) {
        super(DeliveryInfo.class, metadata);
    }

}

