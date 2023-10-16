package com.gigateam.cardealershipsystemapi.common.dto.client;

import lombok.Data;

@Data
public class ClientDto {

  private Long id;
  private String name;
  private String surname;
  private String address;
  private String phoneNumber;
  private String email;
}
