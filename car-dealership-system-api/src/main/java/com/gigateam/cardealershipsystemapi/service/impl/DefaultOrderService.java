package com.gigateam.cardealershipsystemapi.service.impl;

import com.gigateam.cardealershipsystemapi.common.dto.order.OrderDto;
import com.gigateam.cardealershipsystemapi.domain.Order;
import com.gigateam.cardealershipsystemapi.domain.OrderStatus;
import com.gigateam.cardealershipsystemapi.exception.BadRequestException;
import com.gigateam.cardealershipsystemapi.repository.OrderRepository;
import com.gigateam.cardealershipsystemapi.service.CarService;
import com.gigateam.cardealershipsystemapi.service.OrderService;
import com.gigateam.cardealershipsystemapi.service.mapper.OrderMapper;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultOrderService implements OrderService {

  private final OrderRepository orderRepository;
  private final OrderMapper orderMapper;
  private final CarService carService;

  @Override
  @Transactional
  public Long createOrder(Long carId, Long userId) {
    boolean isCarAlreadyOrdered = orderRepository.findByCarId(carId)
        .map(Order::isNotCancelled)
        .orElse(false);

    if (isCarAlreadyOrdered) {
      throw new BadRequestException(String.format("Car with id: %d already ordered", userId));
    }

    Order order = constructOrder(carId, userId);
    carService.markCarAsSold(carId);

    return orderRepository.save(order).getId();
  }

  private Order constructOrder(Long carId, Long userId) {
    Order order = new Order(carId, userId);
    order.setStatus(OrderStatus.CREATED);
    order.setCreationDate(LocalDateTime.now());

    return order;
  }

}
