package com.gigateam.cardealershipsystemapi.rsql.converter.impl;

import com.gigateam.cardealershipsystemapi.domain.UserRole;
import com.gigateam.cardealershipsystemapi.rsql.converter.RsqlConverter;
import org.springframework.stereotype.Component;

@Component
public class UserRoleEnumConverter implements RsqlConverter<UserRole> {

  @Override
  public Class<UserRole> getType() {
    return UserRole.class;
  }

  @Override
  public UserRole convert(String value) {
    return UserRole.valueOf(value);
  }

}
