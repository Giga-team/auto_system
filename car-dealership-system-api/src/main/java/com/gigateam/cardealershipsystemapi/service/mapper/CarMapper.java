package com.gigateam.cardealershipsystemapi.service.mapper;

import com.gigateam.cardealershipsystemapi.common.dto.car.CarDto;
import com.gigateam.cardealershipsystemapi.domain.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class CarMapper {

  @Mapping(target = "id", ignore = true)
  public abstract Car toEntity(CarDto dto);

  public abstract CarDto toDto(Car entity);

}
