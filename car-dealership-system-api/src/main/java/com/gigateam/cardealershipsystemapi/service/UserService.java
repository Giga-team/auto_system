package com.gigateam.cardealershipsystemapi.service;

import com.gigateam.cardealershipsystemapi.common.dto.user.UserDto;
import java.util.Optional;

public interface UserService {

  Optional<UserDto> getUserById(Long id);

  Long createUser(UserDto user);

  Long updateUser(Long id, UserDto user);

  boolean deleteUserById(Long id);

  boolean userExistsById(Long userId);

  boolean userNotExistsById(Long userId);

}
