package com.gigateam.cardealershipsystemapi.common.dto.user;

import com.gigateam.cardealershipsystemapi.domain.UserRole;
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

}
