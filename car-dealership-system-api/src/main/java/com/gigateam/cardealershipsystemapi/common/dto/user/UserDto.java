package com.gigateam.cardealershipsystemapi.common.dto.user;

import com.gigateam.cardealershipsystemapi.domain.UserRole;
import com.gigateam.cardealershipsystemapi.security.DefaultUserDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

  protected Long id;
  protected String name;
  protected String surname;
  protected String phoneNumber;
  protected String email;
  protected UserRole role;

  public static UserDto fromUserDetails(DefaultUserDetails userDetails) {
    return new UserDto(
        userDetails.getId(),
        userDetails.getName(),
        userDetails.getSurname(),
        userDetails.getPhoneNumber(),
        userDetails.getEmail(),
        userDetails.getAuthorities().stream().map(item -> UserRole.valueOf(item.getAuthority())).findFirst().orElseThrow()
    );
  }

}
