package com.gigateam.cardealershipsystemapi.common.dto.car;

import com.gigateam.cardealershipsystemapi.domain.CarStatus;
import com.gigateam.cardealershipsystemapi.domain.GearBox;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CarDto {

  private Long id;
  private String article;
  private String brand;
  private String model;
  private double engineCapacity;
  private int power;
  private int numberOfSeats;
  private GearBox gearBox;
  private BigDecimal price;
  private CarStatus status;

}
