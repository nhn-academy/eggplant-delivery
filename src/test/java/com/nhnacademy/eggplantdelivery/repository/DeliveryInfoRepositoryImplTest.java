package com.nhnacademy.eggplantdelivery.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.eggplantdelivery.dto.response.DeliveryLocationResponseDto;
import com.nhnacademy.eggplantdelivery.entity.DeliveryInfo;
import com.nhnacademy.eggplantdelivery.entity.Location;
import com.nhnacademy.eggplantdelivery.entity.status.Status;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class DeliveryInfoRepositoryImplTest {

    @Autowired
    DeliveryInfoRepository deliveryInfoRepository;

    @Autowired
    LocationRepository locationRepository;

    @Test
    @DisplayName("배송정보, 배송위치 정보 조회")
    void testRetrieveDeliveryLocationResponseDto() {
        DeliveryInfo deliveryInfo = DeliveryInfo.builder()
                                                .trackingNo("1")
                                                .status(Status.DELIVERING)
                                                .completionTime(null)
                                                .receiverName("김훈민")
                                                .receiverDetailAddress("상세주소")
                                                .receiverAddress("그냥주소")
                                                .receiverPhone("010-1234-4321")
                                                .shopHost("호스트주소")
                                                .orderNo("3")
                                                .build();
        DeliveryInfo savedDeliveryInfo = deliveryInfoRepository.save(deliveryInfo);

        Location.Pk pk = new Location.Pk(1L, savedDeliveryInfo.getTrackingNo());
        Location location = Location.builder()
                                    .pk(pk)
                                    .deliveryInfo(savedDeliveryInfo)
                                    .arrivalTime(LocalDateTime.now())
                                    .build();

        locationRepository.save(location);


        // when
        List<DeliveryLocationResponseDto> deliveryLocationResponseDtoList =
            deliveryInfoRepository.retrieveDeliveryLocationResponseDto(deliveryInfo.getTrackingNo());

        // then
        assertThat(deliveryLocationResponseDtoList).hasSize(1);
        assertThat(deliveryLocationResponseDtoList.get(0).getStatus()).isEqualTo(Status.DELIVERING);
    }

}