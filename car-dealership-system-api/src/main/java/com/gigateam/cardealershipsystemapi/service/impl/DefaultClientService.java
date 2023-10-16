package com.gigateam.cardealershipsystemapi.service.impl;


import com.gigateam.cardealershipsystemapi.common.dto.client.ClientDto;
import com.gigateam.cardealershipsystemapi.domain.Client;
import com.gigateam.cardealershipsystemapi.repository.ClientRepository;
import com.gigateam.cardealershipsystemapi.service.ClientService;
import com.gigateam.cardealershipsystemapi.service.mapper.ClientMapper;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultClientService implements ClientService {

  private final ClientRepository repository;
  private final ClientMapper clientMapper;


  @Override
  public Optional<ClientDto> getClientById(Long id) {
    return repository.findById(id)
            .map(clientMapper::toDto);
  }

  @Override
  @Transactional
  public Long createClient(ClientDto client) {
    Client entity = clientMapper.toEntity(client);

    return repository.save(entity).getId();
  }

  @Override
  @Transactional
  public Long updateClient(Long id, ClientDto client) {
    if (!repository.existsById(id)) {
      throw new NoSuchElementException("todo"); //TODO: handle with custom exception handler(f.e. @ControllerAdvice)
    }

    Client entity = clientMapper.toEntity(client);
    entity.setId(id);

    return repository.save(entity).getId();
  }

  @Override
  @Transactional
  public boolean deleteClientById(Long id) {
    return repository.deleteClientById(id) > 0;
  }

}
