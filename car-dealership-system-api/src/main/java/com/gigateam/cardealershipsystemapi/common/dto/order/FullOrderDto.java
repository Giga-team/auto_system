package com.gigateam.cardealershipsystemapi.common.dto.order;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class FullOrderDto extends OrderDto {

  private String name;
  private String surname;
  private String email;
  private String phoneNumber;

}
