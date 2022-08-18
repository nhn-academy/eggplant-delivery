package com.nhnacademy.eggplantdelivery.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nhnacademy.eggplantdelivery.entity.status.Status;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 배송 정보 수정을 위한 응답 Dto 입니다.
 *
 * @author 김훈민, 조재철
 * @version 1.0.0
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryInfoStatusResponseDto {

    @NotBlank(message = "운송장 번호는 필수 사항 입니다.")
    private String orderNo;

    @NotNull(message = "배송 상태는 필수 사항 입니다.")
    private Status status;

    @NotBlank(message = "쇼핑몰 호스트 주소는 필수 사항 입니다.")
    private String shopHost;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime arrivalTime;

}
