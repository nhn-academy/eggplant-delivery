package com.nhnacademy.eggplantdelivery.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
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

    @EmbeddedId
    private Pk pk;

    @MapsId(value = "trackingNo")
    @ManyToOne
    @JoinColumn(name = "tracking_no")
    private DeliveryInfo deliveryInfo;

    @Column(name = "arrival_time")
    @NotNull(message = "도착시간은 필수 입니다.")
    private LocalDateTime arrivalTime;

    /**
     * Location 고유키, 복합키를 담은 클래스 입니다.
     *
     * @author 김훈민, 조재철
     */
    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @EqualsAndHashCode
    public static class Pk implements Serializable {

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "location_no")
        @NotNull(message = "위치 번호는 필수 입니다.")
        private Long locationNo;

        @Column(name = "tracking_no")
        @NotNull(message = "운송장 번호는 필수 입니다.")
        private String trackingNo;

    }

}
