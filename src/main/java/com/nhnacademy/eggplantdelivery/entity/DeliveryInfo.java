package com.nhnacademy.eggplantdelivery.entity;

import com.nhnacademy.eggplantdelivery.entity.status.Status;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 배송정보 엔티티 입니다.
 *
 * @version 1.0.0
 */
@Entity
@Table(name = "delivery_info")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "slip_no")
    private Long slipNo;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column(name = "receiver_name")
    private String receiverName;

    @Column(name = "receiver_address")
    private String receiverAddress;

}
