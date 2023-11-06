package com.gigateam.cardealershipsystemapi.repository;

import com.gigateam.cardealershipsystemapi.common.repository.SearchRepository;
import com.gigateam.cardealershipsystemapi.domain.Car;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends SearchRepository<Car, Long> {

  int deleteCarById(Long id);

}
