package com.nhnacademy.eggplantdelivery.controller;

import com.nhnacademy.eggplantdelivery.dto.request.OrderInfoDto;
import com.nhnacademy.eggplantdelivery.module.Sender;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 배송 요청관련 처리를 위한 컨트롤러 입니다.
 *
 * @version 1.0.0
 */
@RestController
@RequestMapping("/eggplant-delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final Sender sender;

    /**
     * 운송장번호를 생성 요청을 처리하는 컨트롤러 메서드 입니다.
     *
     * @param orderInfoDto 쇼핑몰 서버에서 넘어온 주문 정보입니다.
     * @return 운송장번호를 반환 합니다..
     */
    @PostMapping("/tracking-no")
    public ResponseEntity<Void> createTrackingNo(@RequestBody final OrderInfoDto orderInfoDto) {
        sender.send(orderInfoDto);

        return ResponseEntity.status(HttpStatus.CREATED)
            .build();
    }

}