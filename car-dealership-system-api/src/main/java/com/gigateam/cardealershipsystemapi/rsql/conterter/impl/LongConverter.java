package com.gigateam.cardealershipsystemapi.rsql.conterter.impl;

import com.gigateam.cardealershipsystemapi.rsql.conterter.RsqlConverter;
import org.springframework.stereotype.Component;

@Component
public class LongConverter implements RsqlConverter<Long> {

  @Override
  public Class<Long> getType() {
    return Long.class;
  }

  @Override
  public Long convert(String value) {
    return Long.valueOf(value);
  }

}
