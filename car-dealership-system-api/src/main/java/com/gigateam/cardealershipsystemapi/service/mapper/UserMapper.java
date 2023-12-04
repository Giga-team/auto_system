package com.gigateam.cardealershipsystemapi.service.mapper;

import com.gigateam.cardealershipsystemapi.common.dto.auth.CreateUserRequest;
import com.gigateam.cardealershipsystemapi.common.dto.user.UserDto;
import com.gigateam.cardealershipsystemapi.domain.User;
import com.gigateam.cardealershipsystemapi.domain.UserRole;
import com.gigateam.cardealershipsystemapi.security.DefaultUserDetails;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

  @Mapping(target = "id", ignore = true)
  public abstract User toEntity(UserDto dto);

  public abstract UserDto toDto(User entity);

  public DefaultUserDetails toUserDetails(User entity) {
    DefaultUserDetails details = toUserDetailsInternal(entity);
    details.setAuthorities(toAuthorities(entity.getRole()));

    return details;
  }

  protected abstract DefaultUserDetails toUserDetailsInternal(User entity);

  protected Set<? extends GrantedAuthority> toAuthorities(UserRole role) {
    return Objects.isNull(role)
        ? Collections.emptySet()
        : Set.of(new SimpleGrantedAuthority(role.name()));
  }

  public abstract User toEntity(CreateUserRequest request);

}
