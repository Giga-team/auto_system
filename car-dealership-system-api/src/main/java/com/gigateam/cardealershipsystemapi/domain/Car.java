package com.gigateam.cardealershipsystemapi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Data;

@Data
@Entity
@Table(name = "cars")
public class Car {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "article", nullable = false, unique = true)
  private String article;

  @Column(name = "brand", nullable = false)
  private String brand;

  @Column(name = "model", nullable = false)
  private String model;

  @Column(name = "engine_capacity", nullable = false)
  private double engineCapacity;

  @Column(name = "power", nullable = false)
  private int power;

  @Column(name = "number_of_seats", nullable = false)
  private int numberOfSeats;

  @Enumerated(EnumType.STRING)
  @Column(name = "gear_box", nullable = false)
  private GearBox gearBox;

  @Column(name = "price", nullable = false)
  private BigDecimal price;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private CarStatus status;

}
