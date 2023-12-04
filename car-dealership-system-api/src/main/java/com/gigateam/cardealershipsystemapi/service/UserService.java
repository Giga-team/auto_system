package com.gigateam.cardealershipsystemapi.service;

import com.gigateam.cardealershipsystemapi.common.dto.auth.CreateUserRequest;
import com.gigateam.cardealershipsystemapi.common.dto.user.UserDto;
import com.gigateam.cardealershipsystemapi.domain.UserRole;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

  Optional<UserDetails> getUserByEmail(String email);

  void createUser(CreateUserRequest request);

  void createUser(CreateUserRequest request, UserRole role);

  void createManager(CreateUserRequest request);

  Optional<UserDto> getUserById(Long id);

  Long createUser(UserDto user);

  Long updateUser(Long id, UserDto user);

  boolean deleteUserById(Long id);

  boolean userExistsById(Long userId);

  boolean userNotExistsById(Long userId);

  UserDto getRandomManager();

  List<UserDto> getUsersPage(String query, int page, int limit);

  Long getUsersCount(String query);

}
