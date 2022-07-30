package com.nhnacademy.eggplantdelivery.mapper.impl;

import com.nhnacademy.eggplantdelivery.dto.request.OrderInfoDto;
import com.nhnacademy.eggplantdelivery.entity.DeliveryInfo;
import com.nhnacademy.eggplantdelivery.mapper.GenericMapper;
import org.mapstruct.Mapper;

/**
 * DeliveryInfo entity 를 dto 로 변환하거나 혹은 dto 를 entity 로 변환하는 클래스 입니다.
 *
 * @version 1.0.0
 */
@Mapper(componentModel = "spring")
public interface DeliveryInfoMapper extends GenericMapper<OrderInfoDto, DeliveryInfo> {

}
