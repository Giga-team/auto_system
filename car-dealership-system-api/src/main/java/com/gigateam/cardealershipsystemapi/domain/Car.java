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

  @Column(name = "brand", nullable = false)
  private String brand;

  @Column(name = "model", nullable = false)
  private String model;

  @Column(name = "number", nullable = false)
  private String number; //AA1234BB

  @Column(name = "engine_capacity", nullable = false)
  private double engineCapacity;

  @Column(name = "power", nullable = false)
  private int power;

  @Column(name = "max_passanger", nullable = false)
  private int maxPassenger;

  @Enumerated(EnumType.STRING)
  @Column(name = "gear_box", nullable = false)
  private GearBox gearBox;

  @Column(name = "for_rent", nullable = false)
  private boolean forRent;

  @Column(name = "price", nullable = false)
  private BigDecimal price;

  @Column(name = "mileage", nullable = false)
  private int mileage;

}
