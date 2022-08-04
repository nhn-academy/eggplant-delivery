package com.nhnacademy.eggplantdelivery.entity;

import com.nhnacademy.eggplantdelivery.entity.status.Status;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 배송정보 엔티티 입니다.
 *
 * @author 김훈민, 조재철
 * @version 1.0.0
 */
@Entity
@Table(name = "delivery_info")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryInfo {

    @Id
    @Column(name = "tracking_no")
    private String trackingNo;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column(name = "completion_time")
    private LocalDateTime completionTime;

    @Column(name = "receiver_name")
    private String receiverName;

    @Column(name = "receiver_address")
    private String receiverAddress;

    @Column(name = "receiver_phone")
    private String receiverPhone;

    @Column(name = "shop_host")
    private String shopHost;

    @Column(name = "order_no")
    private String orderNo;

    public void updateStatus(final Status status) {
        this.status = status;
    }
}

