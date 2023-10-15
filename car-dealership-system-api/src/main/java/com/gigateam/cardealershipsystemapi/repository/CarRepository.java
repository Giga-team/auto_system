package com.gigateam.cardealershipsystemapi.repository;

import com.gigateam.cardealershipsystemapi.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

  int deleteCarById(Long id);

}
