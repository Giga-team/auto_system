package com.gigateam.cardealershipsystemapi.service;

import com.gigateam.cardealershipsystemapi.common.dto.user.UserDto;
import java.util.Optional;

public interface UserService {

  Optional<UserDto> getUserById(Long id);

  Long createUser(UserDto client);

  Long updateUser(Long id, UserDto client);

  boolean deleteUserById(Long id);

}
