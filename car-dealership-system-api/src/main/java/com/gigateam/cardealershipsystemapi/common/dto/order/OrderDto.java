package com.gigateam.cardealershipsystemapi.common.dto.order;

import com.gigateam.cardealershipsystemapi.domain.OrderStatus;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class OrderDto {

  private Long id;
  private Long carId;
  private Long userId;
  private OrderStatus status;
  private LocalDateTime creationDate;

}
