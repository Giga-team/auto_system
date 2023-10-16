package com.gigateam.cardealershipsystemapi.repository;

import com.gigateam.cardealershipsystemapi.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

  int deleteClientById(Long id);

}
