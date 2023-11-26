package com.gigateam.cardealershipsystemapi.rsql.converter.impl;

import com.gigateam.cardealershipsystemapi.domain.OrderStatus;
import com.gigateam.cardealershipsystemapi.rsql.converter.RsqlConverter;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusEnumConverter implements RsqlConverter<OrderStatus> {

  @Override
  public Class<OrderStatus> getType() {
    return OrderStatus.class;
  }

  @Override
  public OrderStatus convert(String value) {
    return OrderStatus.valueOf(value);
  }

}
