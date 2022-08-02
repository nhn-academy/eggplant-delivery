package com.nhnacademy.eggplantdelivery.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 배송위치 엔티티 입니다.
 *
 * @version 1.0.0
 */
@Entity
@Table(name = "location")
@NoArgsConstructor
public class Location {

    @Id
    @Column(name = "location_no")
    private Long locationNo;

    @ManyToOne
    @JoinColumn(name = "tracking_no")
    private DeliveryInfo deliveryInfo;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

}

