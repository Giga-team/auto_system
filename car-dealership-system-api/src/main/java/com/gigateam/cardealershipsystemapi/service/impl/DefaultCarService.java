package com.gigateam.cardealershipsystemapi.service.impl;


import com.gigateam.cardealershipsystemapi.common.dto.car.CarDto;
import com.gigateam.cardealershipsystemapi.domain.Car;
import com.gigateam.cardealershipsystemapi.repository.CarRepository;
import com.gigateam.cardealershipsystemapi.service.CarService;
import com.gigateam.cardealershipsystemapi.service.mapper.CarMapper;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultCarService implements CarService {

  private final CarRepository repository;
  private final CarMapper carMapper;


  @Override
  public Optional<CarDto> getCarById(Long id) {
    return repository.findById(id)
        .map(carMapper::toDto);
  }

  @Override
  @Transactional
  public Long createCar(CarDto car) {
    Car entity = carMapper.toEntity(car);

    return repository.save(entity).getId();
  }

  @Override
  @Transactional
  public Long updateCar(Long id, CarDto car) {
    if (!repository.existsById(id)) {
      throw new NoSuchElementException("todo"); //TODO: handle with custom exception handler(f.e. @ControllerAdvice)
    }

    Car entity = carMapper.toEntity(car);
    entity.setId(id);

    return repository.save(entity).getId();
  }

  @Override
  @Transactional
  public boolean deleteCarById(Long id) {
    return repository.deleteCarById(id) > 0;
  }

}
