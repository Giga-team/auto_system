package com.gigateam.cardealershipsystemapi.common.dto.order;

import com.gigateam.cardealershipsystemapi.domain.OrderStatus;
import lombok.Data;

@Data
public class ChangeOrderStatusRequest {

  private OrderStatus status;

}
