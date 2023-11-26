package com.gigateam.cardealershipsystemapi.service;


import com.gigateam.cardealershipsystemapi.common.dto.order.FullOrderDto;
import java.util.List;
import java.util.Optional;

public interface OrderService {

  Long createOrder(Long carId, Long userId);

  Optional<FullOrderDto> getOrderById(Long id);

  List<FullOrderDto> getOrdersPage(String query, int page, int limit);

}
