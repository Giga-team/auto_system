package com.gigateam.cardealershipsystemapi.service.mapper;

import com.gigateam.cardealershipsystemapi.common.dto.client.ClientDto;
import com.gigateam.cardealershipsystemapi.domain.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class ClientMapper {

  @Mapping(target = "id", ignore = true)
  public abstract Client toEntity(ClientDto dto);

  public abstract ClientDto toDto(Client entity);

}
