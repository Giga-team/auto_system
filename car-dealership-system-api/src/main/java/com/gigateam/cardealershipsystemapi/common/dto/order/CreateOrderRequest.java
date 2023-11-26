package com.gigateam.cardealershipsystemapi.common.dto.order;

import lombok.Data;

@Data
public class CreateOrderRequest {

  private Long carId;
  private Long userId;

}
