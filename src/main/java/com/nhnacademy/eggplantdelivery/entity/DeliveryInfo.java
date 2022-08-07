package com.nhnacademy.eggplantdelivery.entity;

import com.nhnacademy.eggplantdelivery.entity.status.Status;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "운송장 번호가 유효하지 않습니다.")
    private String trackingNo;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column(name = "completion_time")
    private LocalDateTime completionTime;

    @Column(name = "receiver_name")
    @NotBlank(message = "수취인 이름은 필수 입니다.")
    private String receiverName;

    @Column(name = "receiver_address")
    @NotBlank(message = "수취인 주소는 필수 입니다.")
    private String receiverAddress;

    @Column(name = "receiver_phone")
    @NotBlank(message = "수취인 휴대번호는 필수 입니다.")
    private String receiverPhone;

    @Column(name = "shop_host")
    @NotBlank(message = "해당 쇼핑몰 host 주소는 필수 입니다.")
    private String shopHost;

    @Column(name = "order_no")
    @NotBlank(message = "해당 주문에 대한 주문 번호는 필수 입니다.")
    private String orderNo;

    public void updateStatus(final Status status) {
        this.status = status;
    }
}

