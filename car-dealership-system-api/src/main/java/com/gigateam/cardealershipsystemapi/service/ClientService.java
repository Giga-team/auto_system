package com.gigateam.cardealershipsystemapi.service;

import com.gigateam.cardealershipsystemapi.common.dto.client.ClientDto;
import java.util.Optional;

public interface ClientService {

  Optional<ClientDto> getClientById(Long id);

  Long createClient(ClientDto client);

  Long updateClient(Long id, ClientDto client);

  boolean deleteClientById(Long id);

}
