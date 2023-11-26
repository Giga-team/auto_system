package com.gigateam.cardealershipsystemapi.rsql.converter.impl;

import com.gigateam.cardealershipsystemapi.domain.CarStatus;
import com.gigateam.cardealershipsystemapi.rsql.converter.RsqlConverter;
import org.springframework.stereotype.Component;

@Component
public class CarStatusEnumConverter implements RsqlConverter<CarStatus> {

  @Override
  public Class<CarStatus> getType() {
    return CarStatus.class;
  }

  @Override
  public CarStatus convert(String value) {
    return CarStatus.valueOf(value);
  }

}
