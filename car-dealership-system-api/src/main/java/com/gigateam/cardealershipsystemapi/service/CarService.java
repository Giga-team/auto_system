package com.gigateam.cardealershipsystemapi.service;

import com.gigateam.cardealershipsystemapi.common.dto.car.CarDto;
import java.util.List;
import java.util.Optional;

public interface CarService {

  Optional<CarDto> getCarById(Long id);

  Long createCar(CarDto car);

  Long updateCar(Long id, CarDto car);

  boolean deleteCarById(Long id);

  List<CarDto> getCarsPage(String query, int page, int limit);

  void markCarAsSold(Long carId);

  void markCarAsAvailable(Long carId);

  boolean carExistsById(Long carId);

  boolean carNotExistsById(Long carId);

  Long getCarsCount(String query);

}
