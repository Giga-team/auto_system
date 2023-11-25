package com.gigateam.cardealershipsystemapi.service.mapper;

import com.gigateam.cardealershipsystemapi.common.dto.order.OrderDto;
import com.gigateam.cardealershipsystemapi.domain.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {

  public abstract OrderDto toDto(Order order);

}
