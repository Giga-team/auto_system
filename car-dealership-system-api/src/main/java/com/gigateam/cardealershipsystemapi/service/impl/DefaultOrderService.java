package com.gigateam.cardealershipsystemapi.service.impl;

import com.gigateam.cardealershipsystemapi.common.dto.order.FullOrderDto;
import com.gigateam.cardealershipsystemapi.domain.Order;
import com.gigateam.cardealershipsystemapi.domain.OrderStatus;
import com.gigateam.cardealershipsystemapi.exception.BadRequestException;
import com.gigateam.cardealershipsystemapi.repository.OrderRepository;
import com.gigateam.cardealershipsystemapi.repository.VwOrderRepository;
import com.gigateam.cardealershipsystemapi.service.CarService;
import com.gigateam.cardealershipsystemapi.service.OrderService;
import com.gigateam.cardealershipsystemapi.service.UserService;
import com.gigateam.cardealershipsystemapi.service.mapper.OrderMapper;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultOrderService implements OrderService {

  private final OrderRepository orderRepository;
  private final VwOrderRepository vwOrderRepository;
  private final OrderMapper orderMapper;
  private final CarService carService;
  private final UserService userService;

  @Override
  @Transactional
  public Long createOrder(Long carId, Long userId) {
    validateCarExistence(carId);
    validateUserExistence(userId);
    validateCarAvailability(carId);

    Order order = constructOrder(carId, userId);
    carService.markCarAsSold(carId);

    return orderRepository.save(order).getId();
  }

  private void validateCarExistence(Long carId) {
    if (carService.carNotExistsById(carId)) {
      throw new BadRequestException(String.format("Car with id: %d doesn't exist", carId));
    }
  }

  private void validateUserExistence(Long userId) {
    if (userService.userNotExistsById(userId)) {
      throw new BadRequestException(String.format("User with id: %d doesn't exist", userId));
    }
  }

  private void validateCarAvailability(Long carId) {
    boolean isCarAlreadyOrdered = orderRepository.findByCarId(carId)
        .map(Order::isNotCancelled)
        .orElse(false);

    if (isCarAlreadyOrdered) {
      throw new BadRequestException(String.format("Car with id: %d already ordered", carId));
    }
  }

  private Order constructOrder(Long carId, Long userId) {
    Order order = new Order(carId, userId);
    order.setStatus(OrderStatus.CREATED);
    order.setCreationDate(LocalDateTime.now());

    return order;
  }

  @Override
  public Optional<FullOrderDto> getOrderById(Long id) {
    return vwOrderRepository.findById(id)
        .map(orderMapper::toDto);
  }

  @Override
  public List<FullOrderDto> getOrdersPage(String query, int page, int limit) {
    return vwOrderRepository.findAll(query, page, limit).stream()
        .map(orderMapper::toDto)
        .collect(Collectors.toList());
  }

}
