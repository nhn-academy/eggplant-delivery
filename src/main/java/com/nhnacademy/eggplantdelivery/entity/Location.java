package com.nhnacademy.eggplantdelivery.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

/**
 * 배송위치 엔티티 입니다.
 *
 * @author 김훈민, 조재철
 * @version 1.0.0
 */
@Entity
@Table(name = "location")
@NoArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_no")
    private Long locationNo;

    @ManyToOne
    @JoinColumn(name = "tracking_no")
    private DeliveryInfo deliveryInfo;

    @Column(name = "arrival_time")
    @NotNull(message = "도착시간은 필수 입니다.")
    private LocalDateTime arrivalTime;

}
