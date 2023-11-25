package com.gigateam.cardealershipsystemapi.repository;

import com.gigateam.cardealershipsystemapi.common.repository.SearchRepository;
import com.gigateam.cardealershipsystemapi.domain.Car;
import com.gigateam.cardealershipsystemapi.domain.CarStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends SearchRepository<Car, Long> {

  int deleteCarById(Long id);

  @Query(value = "UPDATE cars SET status = :status WHERE id = :id", nativeQuery = true)
  void setCarStatus(@Param("id") Long id, @Param("status") CarStatus status);

}
