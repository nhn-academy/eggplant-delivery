package com.nhnacademy.eggplantdelivery.repository;

import com.nhnacademy.eggplantdelivery.dto.response.DeliveryInfoStatusResponseDto;
import java.util.List;

public interface CustomDeliveryRepository {
    List<DeliveryInfoStatusResponseDto> retrieveDeliveryStatus();
}
