package com.gigateam.cardealershipsystemapi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "car_id", nullable = false)
  private Long carId;

  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private OrderStatus status;

  @Column(name = "creation_date", nullable = false)
  private LocalDateTime creationDate;

  public Order(Long carId, Long userId) {
    this.carId = carId;
    this.userId = userId;
  }

  public boolean isCancelled() {
    return status.isCancelled();
  }

  public boolean isNotCancelled() {
    return !isCancelled();
  }

}
