package com.gigateam.cardealershipsystemapi.service.impl;


import com.gigateam.cardealershipsystemapi.common.dto.auth.CreateUserRequest;
import com.gigateam.cardealershipsystemapi.common.dto.user.UserDto;
import com.gigateam.cardealershipsystemapi.domain.User;
import com.gigateam.cardealershipsystemapi.domain.UserRole;
import com.gigateam.cardealershipsystemapi.exception.BadRequestException;
import com.gigateam.cardealershipsystemapi.exception.NotFoundException;
import com.gigateam.cardealershipsystemapi.repository.UserRepository;
import com.gigateam.cardealershipsystemapi.security.DefaultUserDetails;
import com.gigateam.cardealershipsystemapi.service.UserService;
import com.gigateam.cardealershipsystemapi.service.mapper.UserMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

  private final UserRepository repository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return repository.findByEmail(username)
        .map(userMapper::toUserDetails)
        .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email: %s not found", username)));
  }

  @Override
  public Optional<UserDetails> getUserByEmail(String email) {
    return repository.findByEmail(email)
        .map(userMapper::toUserDetails);
  }

  @Override
  @Transactional
  public void createUser(CreateUserRequest request) {
    createUser(request, UserRole.CLIENT);
  }

  @Override
  @Transactional
  public void createUser(CreateUserRequest request, UserRole role) {
    validateEmailUniqueness(request.getEmail());
    validatePhoneNumberUniqueness(request.getPhoneNumber());

    User user = userMapper.toEntity(request);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRole(role);

    repository.save(user);
  }

  private void validateEmailUniqueness(String email) {
    boolean alreadyExists = repository.existsByEmail(email);

    if (alreadyExists) {
      throw new BadRequestException(String.format("User with email: %s already exists", email));
    }
  }

  private void validatePhoneNumberUniqueness(String phoneNumber) {
    boolean alreadyExists = repository.existsByPhoneNumber(phoneNumber);

    if (alreadyExists) {
      throw new BadRequestException(String.format("User with phone number: %s already exists", phoneNumber));
    }
  }

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
