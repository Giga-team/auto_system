package com.gigateam.cardealershipsystemapi.service;


import com.gigateam.cardealershipsystemapi.common.dto.order.FullOrderDto;
import com.gigateam.cardealershipsystemapi.domain.OrderStatus;
import java.util.List;
import java.util.Optional;

public interface OrderService {

  Long createOrder(Long carId, Long userId);

  Optional<FullOrderDto> getOrderById(Long id);

  List<FullOrderDto> getOrdersPage(String query, int page, int limit);

  Long getOrdersCount(String query);

  void changeOrderStatus(Long orderId, OrderStatus status);

}
