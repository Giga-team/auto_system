package com.gigateam.cardealershipsystemapi.common.dto.car;

import com.gigateam.cardealershipsystemapi.domain.GearBox;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CarDto {

  private Long id;
  private String brand;
  private String model;
  private String number;
  private double engineCapacity;
  private int power;
  private int maxPassenger;
  private GearBox gearBox;
  private boolean forRent;
  private BigDecimal price;
  private int mileage;

}
