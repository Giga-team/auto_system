package com.gigateam.cardealershipsystemapi.rsql.conterter.impl;

import com.gigateam.cardealershipsystemapi.rsql.conterter.RsqlConverter;
import org.springframework.stereotype.Component;

@Component
public class BooleanConverter implements RsqlConverter<Boolean> {

  @Override
  public Class<Boolean> getType() {
    return Boolean.class;
  }

  @Override
  public Boolean convert(String value) {
    return Boolean.valueOf(value);
  }

}
