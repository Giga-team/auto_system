package com.gigateam.cardealershipsystemapi.repository;

import com.gigateam.cardealershipsystemapi.common.repository.SearchRepository;
import com.gigateam.cardealershipsystemapi.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends SearchRepository<User, Long> {

  int deleteUserById(Long id);

  Optional<User> findByEmail(String email);

  boolean existsByEmail(String email);

  boolean existsByPhoneNumber(String phoneNumber);

  @Query(value = "SELECT * FROM users WHERE role = 'MANAGER' ORDER BY random() LIMIT 1", nativeQuery = true)
  Optional<User> findRandomManager();

}
