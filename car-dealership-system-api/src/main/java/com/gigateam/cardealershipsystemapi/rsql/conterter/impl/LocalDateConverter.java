package com.gigateam.cardealershipsystemapi.rsql.conterter.impl;

import com.gigateam.cardealershipsystemapi.rsql.conterter.RsqlConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class LocalDateConverter implements RsqlConverter<LocalDate> {

  @Override
  public Class<LocalDate> getType() {
    return LocalDate.class;
  }

  @Override
  public LocalDate convert(String value) {
    return LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE);
  }

}
