package com.nhnacademy.eggplantdelivery.dto.request;

import java.util.UUID;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 주문정보 요청 Dto 입니다.
 *
 * @author 김훈민, 조재철
 * @version 1.0.0
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfoRequestDto {

    private UUID trackingNo;

    @NotBlank(message = "수취인 이름은 필수 입니다.")
    private String receiverName;

    @NotBlank(message = "수취인 주소는 필수 입니다.")
    private String receiverAddress;

    @NotBlank(message = "수취인 휴대번호는 필수 입니다.")
    private String receiverPhone;

    @NotBlank(message = "해당 주문에 대한 주문 번호는 필수 입니다.")
    private String orderNo;

    @NotBlank(message = "해당 쇼핑몰 host 주소는 필수 입니다.")
    private String shopHost;

    public void insertTrackingNo(UUID createdTrackingNo) {
        this.trackingNo = createdTrackingNo;
    }

    public void insertShopHost(String shopHost) {
        this.shopHost = shopHost;
    }

}
