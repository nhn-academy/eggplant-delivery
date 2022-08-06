package com.nhnacademy.eggplantdelivery.repository;

import com.nhnacademy.eggplantdelivery.dto.response.DeliveryInfoStatusResponseDto;
import java.util.List;

/**
 * Query DSL 사용을 위한 interface DeliveryRepository 입니다.
 *
 * @author 김훈민, 조재철
 * @version 1.0.0
 */
public interface CustomDeliveryRepository {
    List<DeliveryInfoStatusResponseDto> retrieveDeliveryStatus();

}
