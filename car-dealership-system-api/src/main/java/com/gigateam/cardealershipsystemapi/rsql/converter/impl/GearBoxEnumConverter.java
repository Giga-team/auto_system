package com.gigateam.cardealershipsystemapi.rsql.converter.impl;

import com.gigateam.cardealershipsystemapi.domain.GearBox;
import com.gigateam.cardealershipsystemapi.rsql.converter.RsqlConverter;
import org.springframework.stereotype.Component;

@Component
public class GearBoxEnumConverter implements RsqlConverter<GearBox> {

  @Override
  public Class<GearBox> getType() {
    return GearBox.class;
  }

  @Override
  public GearBox convert(String value) {
    return GearBox.valueOf(value);
  }

}
