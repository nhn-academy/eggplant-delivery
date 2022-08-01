package com.nhnacademy.eggplantdelivery.entity;

import com.nhnacademy.eggplantdelivery.entity.status.Status;
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
import lombok.Setter;

/**
 * 배송정보 엔티티 입니다.
 *
 * @version 1.0.0
 */
@Entity
@Table(name = "delivery_info")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryInfo {

    @Id
    @Column(name = "tracking_no")
    private Long trackingNo;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Status status;

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


}

