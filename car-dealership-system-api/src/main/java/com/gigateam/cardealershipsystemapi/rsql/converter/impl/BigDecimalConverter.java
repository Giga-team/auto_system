package com.gigateam.cardealershipsystemapi.rsql.converter.impl;

import com.gigateam.cardealershipsystemapi.rsql.converter.RsqlConverter;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class BigDecimalConverter implements RsqlConverter<BigDecimal> {

  @Override
  public Class<BigDecimal> getType() {
    return BigDecimal.class;
  }

  @Override
  public BigDecimal convert(String value) {
    return new BigDecimal(value);
  }

}
