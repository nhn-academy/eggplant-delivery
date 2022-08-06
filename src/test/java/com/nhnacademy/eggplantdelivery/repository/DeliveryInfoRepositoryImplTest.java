package com.nhnacademy.eggplantdelivery.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.eggplantdelivery.dto.response.DeliveryInfoStatusResponseDto;
import com.nhnacademy.eggplantdelivery.entity.DeliveryInfo;
import com.nhnacademy.eggplantdelivery.entity.status.Status;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class DeliveryInfoRepositoryImplTest {

    @Autowired
    DeliveryInfoRepository deliveryInfoRepository;

    @Test
    void retrieveDeliveryStatus() {
        DeliveryInfo deliveryInfo = DeliveryInfo.builder()
                                                .trackingNo("1")
                                                .status(Status.DELIVERING)
                                                .build();

        DeliveryInfo deliveryInfo1 = DeliveryInfo.builder()
                                                 .trackingNo("2")
                                                 .status(Status.ARRIVAL)
                                                 .build();
        // given
        deliveryInfoRepository.saveAll(List.of(deliveryInfo, deliveryInfo1));

        // when
        List<DeliveryInfoStatusResponseDto> deliveryInfoStatusResponseDtoList =
            deliveryInfoRepository.retrieveDeliveryStatus();

        // then
        assertThat(deliveryInfoStatusResponseDtoList).hasSize(1);
        assertThat(deliveryInfoStatusResponseDtoList.get(0).getTrackingNo()).isEqualTo("1");
        assertThat(deliveryInfoStatusResponseDtoList.get(0).getStatus()).isEqualTo(Status.DELIVERING);
    }

}
