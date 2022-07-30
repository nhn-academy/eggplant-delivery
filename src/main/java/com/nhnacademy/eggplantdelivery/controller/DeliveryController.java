package com.nhnacademy.eggplantdelivery.controller;

import com.nhnacademy.eggplantdelivery.dto.request.OrderInfoRequestDto;
import com.nhnacademy.eggplantdelivery.module.Sender;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/tracking-no")
    public String getDeliveryTrackingNo(@RequestBody final OrderInfoRequestDto orderInfoRequestDto) {
        sender.send(orderInfoRequestDto);
        return null;
    }

}
