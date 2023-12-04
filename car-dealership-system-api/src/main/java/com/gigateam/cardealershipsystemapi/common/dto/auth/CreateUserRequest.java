package com.gigateam.cardealershipsystemapi.common.dto.auth;

import lombok.Data;

@Data
public class CreateUserRequest {

  private String name;
  private String surname;
  private String phoneNumber;
  private String email;
  private String password;

}
