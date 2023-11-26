package com.gigateam.cardealershipsystemapi.service.impl;


import com.gigateam.cardealershipsystemapi.common.dto.user.UserDto;
import com.gigateam.cardealershipsystemapi.domain.User;
import com.gigateam.cardealershipsystemapi.exception.NotFoundException;
import com.gigateam.cardealershipsystemapi.repository.UserRepository;
import com.gigateam.cardealershipsystemapi.service.UserService;
import com.gigateam.cardealershipsystemapi.service.mapper.UserMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

  private final UserRepository repository;
  private final UserMapper userMapper;


  @Override
  public Optional<UserDto> getUserById(Long id) {
    return repository.findById(id)
            .map(userMapper::toDto);
  }

  @Override
  @Transactional
  public Long createUser(UserDto user) {
    User entity = userMapper.toEntity(user);

    return repository.save(entity).getId();
  }

  @Override
  @Transactional
  public Long updateUser(Long id, UserDto user) {
    if (!repository.existsById(id)) {
      throw new NotFoundException(String.format("User with id: %s not found", id));
    }

    User entity = userMapper.toEntity(user);
    entity.setId(id);

    return repository.save(entity).getId();
  }

  @Override
  @Transactional
  public boolean deleteUserById(Long id) {
    return repository.deleteUserById(id) > 0;
  }

  @Override
  public boolean userExistsById(Long userId) {
    return repository.existsById(userId);
  }

  @Override
  public boolean userNotExistsById(Long userId) {
    return !userExistsById(userId);
  }

}
