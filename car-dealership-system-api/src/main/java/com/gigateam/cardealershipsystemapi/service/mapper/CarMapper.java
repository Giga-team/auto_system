package com.gigateam.cardealershipsystemapi.service.mapper;

import com.gigateam.cardealershipsystemapi.common.dto.car.CarDto;
import com.gigateam.cardealershipsystemapi.domain.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public abstract class CarMapper {

  @Mappings({
      @Mapping(target = "id", ignore = true),
      @Mapping(target = "status", ignore = true)
  })
  public abstract Car toEntity(CarDto dto);

  public abstract CarDto toDto(Car entity);

}
