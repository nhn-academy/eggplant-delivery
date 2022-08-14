package com.nhnacademy.eggplantdelivery.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryLocationResponseDto {

    private String status;
    private LocalDateTime completionTime;
    private List<LocationResponseDto> locationResponseDtoList;

}

