package com.nhnacademy.eggplantdelivery.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@AllArgsConstructor
@Builder
public class Location {

    @EmbeddedId
    private Pk pk;

    @MapsId(value = "trackingNo")
    @ManyToOne
    @JoinColumn(name = "tracking_no")
    private DeliveryInfo deliveryInfo;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    @Column(name = "middle_location")
    private String middleLocation;

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

        @Column(name = "location_no")
        private Long locationNo;

        @Column(name = "tracking_no")
        private String trackingNo;

    }

}
