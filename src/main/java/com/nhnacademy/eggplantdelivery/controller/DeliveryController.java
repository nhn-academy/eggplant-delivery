package com.nhnacademy.eggplantdelivery.controller;

import com.nhnacademy.eggplantdelivery.dto.request.DeliveryStatusUpdateRequestDto;
import com.nhnacademy.eggplantdelivery.dto.request.OrderInfoRequestDto;
import com.nhnacademy.eggplantdelivery.module.Sender;
import com.nhnacademy.eggplantdelivery.service.DeliveryService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
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

    private final DeliveryService service;

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

    /**
     * 배송상태 정보를 수정하기 위한 컨트롤러 메서드 입니다.
     *
     * @param deliveryStatusUpdateRequestDto 배송상태 수정을 위한 상태와 운송장 번호를 담은 객체 List 입니다.
     * @return 상태코드를 반환합니다.
     * @author 김훈민, 조재철
     */
    @PatchMapping("/delivery-info-status")
    public ResponseEntity<Void> updateDeliveryUpdate(
        @RequestBody final List<DeliveryStatusUpdateRequestDto> deliveryStatusUpdateRequestDto) {
        sender.sendUpdateStatus(deliveryStatusUpdateRequestDto);

        return ResponseEntity.status(HttpStatus.OK)
                             .build();
    }

}
