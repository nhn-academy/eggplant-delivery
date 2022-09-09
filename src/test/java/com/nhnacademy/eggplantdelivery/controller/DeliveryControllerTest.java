package com.nhnacademy.eggplantdelivery.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.eggplantdelivery.dto.request.OrderInfoRequestDto;
import com.nhnacademy.eggplantdelivery.module.Publisher;
import com.nhnacademy.eggplantdelivery.repository.DeliveryInfoRepository;
import com.nhnacademy.eggplantdelivery.service.DeliveryService;
import java.util.Collections;
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
    Publisher publisher;

    @Test
    @DisplayName("운송장 번호 생성")
    void testCreateTrackingNo() throws Exception {
        doNothing().when(publisher).send(any(OrderInfoRequestDto.class));

        OrderInfoRequestDto orderInfoRequestDto = new OrderInfoRequestDto(
            null, "1", "1", "1", "1", "1", "1");

        String jsonRequest = mapper.writeValueAsString(orderInfoRequestDto);

        mockMvc.perform(post("/eggplant-delivery/tracking-no")
                   .contentType(APPLICATION_JSON)
                   .content(jsonRequest))
               .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("배송정보, 배송위치 조회 후 List 로 요청 서버에 전송")
    void retrieveDeliveryLocation() throws Exception {
        when(service.retrieveDeliveryLocation(anyString())).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/eggplant-delivery/tracking-no?trackingNo=1")
                   .contentType(APPLICATION_JSON))
               .andExpect(status().isOk());
    }

}
