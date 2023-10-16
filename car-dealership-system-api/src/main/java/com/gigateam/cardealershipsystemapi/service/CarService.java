package com.gigateam.cardealershipsystemapi.service;

import com.gigateam.cardealershipsystemapi.common.dto.car.CarDto;
import java.util.Optional;

public interface CarService {

  Optional<CarDto> getCarById(Long id);

  Long createCar(CarDto car);

  Long updateCar(Long id, CarDto car);

  boolean deleteCarById(Long id);

}
