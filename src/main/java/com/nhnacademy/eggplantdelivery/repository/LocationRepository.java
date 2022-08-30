package com.nhnacademy.eggplantdelivery.repository;

import com.nhnacademy.eggplantdelivery.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 배송 정보 위치를 담은 JpaRepository 입니다.
 *
 * @author 김훈민, 조재철
 * @version 1.0.0
 */
public interface LocationRepository extends JpaRepository<Location, Long> {

}
