package com.nhnacademy.eggplantdelivery.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.eggplantdelivery.dto.request.DeliveryStatusUpdateRequestDto;
import com.nhnacademy.eggplantdelivery.dto.request.OrderInfoRequestDto;
import com.nhnacademy.eggplantdelivery.dto.response.DeliveryInfoStatusResponseDto;
import com.nhnacademy.eggplantdelivery.module.Sender;
import com.nhnacademy.eggplantdelivery.repository.DeliveryInfoRepository;
import com.nhnacademy.eggplantdelivery.service.DeliveryService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(DeliveryController.class)
class DeliveryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    DeliveryService service;

    @MockBean
    DeliveryInfoRepository repository;

    @MockBean
    HttpServletRequest servletRequest;

    @MockBean
    Sender sender;

    @Test
    @DisplayName("운송장 번호 생성")
    void testCreateTrackingNo() throws Exception {
        when(servletRequest.getRemoteHost()).thenReturn("localhost");

        when(servletRequest.getServerPort()).thenReturn(8080);

        doNothing().when(sender).send(any(OrderInfoRequestDto.class));

        doNothing().when(service).sendTrackingNo(any(OrderInfoRequestDto.class));

        OrderInfoRequestDto orderInfoRequestDto = new OrderInfoRequestDto(
            null, "1", "1", "1", "1", "1");

        String jsonRequest = mapper.writeValueAsString(orderInfoRequestDto);

        mockMvc.perform(post("/eggplant-delivery/tracking-no")
                   .contentType(APPLICATION_JSON)
                   .content(jsonRequest))
               .andExpect(status().isCreated());

    }

    @Test
    @DisplayName("배송 상태 수정")
    void testUpdateDeliveryUpdate() throws Exception {
        DeliveryStatusUpdateRequestDto deliveryStatusUpdateRequestDto = new DeliveryStatusUpdateRequestDto();
        List<DeliveryStatusUpdateRequestDto> deliveryStatusUpdateRequestDtoList =
            List.of(deliveryStatusUpdateRequestDto);

        doNothing().when(sender).sendUpdateStatus(anyList());

        String jsonRequest = mapper.writeValueAsString(deliveryStatusUpdateRequestDtoList);

        mockMvc.perform(patch("/eggplant-delivery/delivery-info-status")
                   .contentType(APPLICATION_JSON)
                   .content(jsonRequest))
               .andExpect(status().isOk());
    }

    @Test
    @DisplayName("배송 상태 조회")
    void testRetrieveDeliveryStatus() throws Exception {
        DeliveryInfoStatusResponseDto deliveryInfoStatusResponseDto = new DeliveryInfoStatusResponseDto();
        List<DeliveryInfoStatusResponseDto> deliveryInfoStatusResponseDtoList = List.of(deliveryInfoStatusResponseDto);

        when(service.retrieveDeliveryStatus()).thenReturn(deliveryInfoStatusResponseDtoList);

        String jsonRequest = mapper.writeValueAsString(deliveryInfoStatusResponseDtoList);

        mockMvc.perform(get("/eggplant-delivery/delivery-info-status")
            .contentType(APPLICATION_JSON)
            .content(jsonRequest))
            .andExpect(status().isOk());

    }

}
