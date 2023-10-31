package com.gigateam.cardealershipsystemapi.rsql.conterter.impl;

import com.gigateam.cardealershipsystemapi.rsql.conterter.RsqlConverter;
import org.springframework.stereotype.Component;

@Component
public class IntegerConverter implements RsqlConverter<Integer> {

  @Override
  public Class<Integer> getType() {
    return Integer.class;
  }

  @Override
  public Integer convert(String value) {
    return Integer.valueOf(value);
  }

}
