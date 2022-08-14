package com.nhnacademy.eggplantdelivery.controller;

import com.nhnacademy.eggplantdelivery.dto.request.OrderInfoRequestDto;
import com.nhnacademy.eggplantdelivery.dto.response.DeliveryLocationResponseDto;
import com.nhnacademy.eggplantdelivery.module.Sender;
import com.nhnacademy.eggplantdelivery.service.DeliveryService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 배송 요청관련 처리를 위한 컨트롤러 입니다.
 *
 * @author 김훈민, 조재철
 * @version 1.0.0
 */
@RestController
@RequestMapping("/eggplant-delivery")
@RequiredArgsConstructor
@Slf4j
public class DeliveryController {

    private final Sender sender;
    private final DeliveryService deliveryService;

    /**
     * 운송장번호를 생성 요청을 처리하는 컨트롤러 메서드 입니다.
     *
     * @param orderInfoRequestDto 쇼핑몰 서버에서 넘어온 주문 정보입니다.
     * @return 운송장번호를 반환 합니다.
     * @author 김훈민, 조재철
     */
    @PostMapping("/tracking-no")
    public ResponseEntity<Void> createTrackingNo(@RequestBody final OrderInfoRequestDto orderInfoRequestDto,
                                                 final HttpServletRequest servletRequest) {

        orderInfoRequestDto.insertShopHost(servletRequest.getRemoteHost());
        sender.send(orderInfoRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .build();
    }

    @GetMapping("/tracking-no?trackingNo={trackingNo}")
    public ResponseEntity<DeliveryLocationResponseDto> retrieveDeliveryLocation(
        @PathVariable @Min(1) final String trackingNo) {
        return ResponseEntity.ok(deliveryService.retrieveDeliveryLocation(trackingNo));
    }

}
