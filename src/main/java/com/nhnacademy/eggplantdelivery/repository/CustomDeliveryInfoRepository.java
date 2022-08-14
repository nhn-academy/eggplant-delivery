package com.nhnacademy.eggplantdelivery.repository;

import com.nhnacademy.eggplantdelivery.dto.response.DeliveryLocationResponseDto;
import java.util.List;

interface CustomDeliveryInfoRepository {

    List<DeliveryLocationResponseDto> retrieveDeliveryLocationResponseDto(String trackingNo);

}
