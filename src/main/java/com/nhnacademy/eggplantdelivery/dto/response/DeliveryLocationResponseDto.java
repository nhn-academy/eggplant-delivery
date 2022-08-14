package com.nhnacademy.eggplantdelivery.dto.response;

import com.nhnacademy.eggplantdelivery.entity.status.Status;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeliveryLocationResponseDto {

    private Status status;
    private LocalDateTime completionTime;
    private Long locationNo;
    private LocalDateTime arrivalTime;

}

