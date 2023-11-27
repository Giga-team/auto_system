package com.gigateam.cardealershipsystemapi.repository;

import com.gigateam.cardealershipsystemapi.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  int deleteUserById(Long id);

  Optional<User> findByEmail(String email);

  boolean existsByEmail(String email);

  boolean existsByPhoneNumber(String phoneNumber);

}
