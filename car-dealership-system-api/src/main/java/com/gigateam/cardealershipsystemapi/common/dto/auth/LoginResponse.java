package com.gigateam.cardealershipsystemapi.common.dto.auth;

import com.gigateam.cardealershipsystemapi.common.dto.user.UserDto;
import com.gigateam.cardealershipsystemapi.domain.UserRole;
import com.gigateam.cardealershipsystemapi.security.DefaultUserDetails;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LoginResponse extends UserDto {

  private String token;

  public LoginResponse(DefaultUserDetails userDetails, UserRole role, String token) {
    this.id = userDetails.getId();
    this.name = userDetails.getName();
    this.surname = userDetails.getSurname();
    this.phoneNumber = userDetails.getPhoneNumber();
    this.email = userDetails.getEmail();
    this.userRole = role;
    this.token = token;
  }

}
