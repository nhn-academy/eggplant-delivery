package com.nhnacademy.eggplantdelivery.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Entity 를 dto 로 혹은 dto 를 Entity 로 변환 처리를 하는 제네릭 클래스 입니다.
 *
 * @version 1.0.0
 */

public interface GenericMapper<D, E> {

    D toDto(E e);

    E toEntity(D d);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    void createFromDto(D dto, @MappingTarget E entity);

}
