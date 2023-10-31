package com.gigateam.cardealershipsystemapi.rsql.converter.impl;

import com.gigateam.cardealershipsystemapi.rsql.converter.RsqlConverter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class LocalDateTimeConverter implements RsqlConverter<LocalDateTime> {

  @Override
  public Class<LocalDateTime> getType() {
    return LocalDateTime.class;
  }

  @Override
  public LocalDateTime convert(String value) {
    return LocalDateTime.parse(value, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
  }

}
