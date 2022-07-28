package com.nhnacademy.eggplantdelivery.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 배송위치 엔티티 입니다.
 *
 * @version 1.0.0
 */
@Entity
@Table(name = "location")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location {

    @Id
    @Column(name = "location_no")
    private Long locationNo;

    @ManyToOne
    @JoinColumn(name = "slip_no")
    private DeliveryInfo deliveryInfo;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

}

