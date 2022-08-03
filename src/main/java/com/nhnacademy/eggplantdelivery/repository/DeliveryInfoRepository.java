package com.nhnacademy.eggplantdelivery.repository;

import com.nhnacademy.eggplantdelivery.entity.DeliveryInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DeliveryInfo DB 처리를 위한 Repository 입니다.
 *
 * @author 김훈민, 조재철
 * @version 1.0.0
 */
public interface DeliveryInfoRepository extends JpaRepository<DeliveryInfo, String> {

}
