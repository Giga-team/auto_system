package com.gigateam.cardealershipsystemapi.service.mapper;

import com.gigateam.cardealershipsystemapi.common.dto.order.FullOrderDto;
import com.gigateam.cardealershipsystemapi.common.dto.order.OrderDto;
import com.gigateam.cardealershipsystemapi.domain.Order;
import com.gigateam.cardealershipsystemapi.domain.VwOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {

  public abstract OrderDto toDto(Order order);

  public abstract FullOrderDto toDto(VwOrder vwOrder);

}
