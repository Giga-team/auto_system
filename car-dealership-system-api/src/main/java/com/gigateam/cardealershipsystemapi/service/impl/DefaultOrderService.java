package com.gigateam.cardealershipsystemapi.service.impl;

import com.gigateam.cardealershipsystemapi.common.dto.car.CarDto;
import com.gigateam.cardealershipsystemapi.common.dto.order.FullOrderDto;
import com.gigateam.cardealershipsystemapi.common.dto.order.OrderDto;
import com.gigateam.cardealershipsystemapi.common.dto.user.UserDto;
import com.gigateam.cardealershipsystemapi.domain.Car;
import com.gigateam.cardealershipsystemapi.domain.Order;
import com.gigateam.cardealershipsystemapi.domain.OrderStatus;
import com.gigateam.cardealershipsystemapi.exception.BadRequestException;
import com.gigateam.cardealershipsystemapi.exception.NotFoundException;
import com.gigateam.cardealershipsystemapi.repository.OrderRepository;
import com.gigateam.cardealershipsystemapi.repository.VwOrderRepository;
import com.gigateam.cardealershipsystemapi.service.CarService;
import com.gigateam.cardealershipsystemapi.service.EmailSenderService;
import com.gigateam.cardealershipsystemapi.service.OrderService;
import com.gigateam.cardealershipsystemapi.service.UserService;
import com.gigateam.cardealershipsystemapi.service.mapper.OrderMapper;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultOrderService implements OrderService {

  private final OrderRepository orderRepository;
  private final VwOrderRepository vwOrderRepository;
  private final OrderMapper orderMapper;
  private final UserService userService;
  private CarService carService;

  @Lazy
  @Autowired
  void setCarService(CarService carService) {
    this.carService = carService;
  }

  private EmailSenderService senderService;

  @Autowired
  public void setSenderService(EmailSenderService senderService) {
    this.senderService = senderService;
  }

  @Override
  @Transactional
  public Long createOrder(Long carId, Long userId) {
    validateCarExistence(carId);
    validateUserExistence(userId);
    validateCarAvailability(carId);

    Order order = constructOrder(carId, userId);
    carService.markCarAsSold(carId);

    sendCarInfoToUser(carId, userId);

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
    boolean isCarAlreadyOrdered = orderRepository.findByCarId(carId).stream()
        .anyMatch(Order::isNotCancelled);

    if (isCarAlreadyOrdered) {
      throw new BadRequestException(String.format("Car with id: %d already ordered", carId));
    }
  }

  private void sendCarInfoToUser(Long carId, Long userId) {
    Optional<CarDto> car = carService.getCarById(carId);
    CarDto carInfo = car.orElseThrow();
    System.out.println(carInfo);
    Optional<UserDto> user = userService.getUserById(userId);
    String userEmail = user.map(u->u.getEmail()).
            orElseThrow();

    senderService.sendDetailsEmail(userEmail, carInfo);
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

  @Override
  public Long getOrdersCount(String query) {
    return vwOrderRepository.count(query);
  }

  @Override
  @Transactional
  public void changeOrderStatus(Long orderId, OrderStatus status) {
    validateStatusChanging(orderId, status);

    orderRepository.setOrderStatus(orderId, status);
  }

  private void validateStatusChanging(Long orderId, OrderStatus status) {
    if (status.isCancelled()) {
      throw new BadRequestException(String.format("Manual changing status value to %s is unavailable", status));
    }

    OrderStatus oldStatus = orderRepository.findById(orderId)
        .map(Order::getStatus)
        .orElseThrow(() -> new NotFoundException(String.format("Order is id: %d not found", orderId)));

    if (oldStatus.isCancelled()) {
      throw new BadRequestException(String.format("Order has status: %s, so changing status value is unavailable", status));
    }
  }

  @Override
  @Transactional
  public void cancelOrder(Long orderId) {
    Order order = orderRepository.findById(orderId)
        .orElseThrow(() -> new NotFoundException(String.format("Order is id: %d not found", orderId)));

    if (order.isCancelled()) {
      throw new BadRequestException(String.format("Order with id: %d is already cancelled", orderId));
    }

    orderRepository.setOrderStatus(orderId, OrderStatus.CANCELLED);
    carService.markCarAsAvailable(order.getCarId());
  }

  @Override
  public List<OrderDto> getOrdersByCarId(Long carId) {
    return orderRepository.findByCarId(carId).stream()
        .map(orderMapper::toDto)
        .collect(Collectors.toList());
  }

  @Override
  public List<OrderDto> getOrdersByUserId(Long userId) {
    return orderRepository.findByUserId(userId).stream()
        .map(orderMapper::toDto)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public void deleteOrdersByCarId(Long carId) {
    orderRepository.deleteByCarId(carId);
  }

  @Override
  @Transactional
  public void deleteOrderByUserId(Long userId) {
    orderRepository.deleteByUserId(userId);
  }

}
