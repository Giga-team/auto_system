package com.gigateam.cardealershipsystemapi.controller;

import com.gigateam.cardealershipsystemapi.common.dto.ApiResponse;
import com.gigateam.cardealershipsystemapi.common.dto.Responses;
import com.gigateam.cardealershipsystemapi.common.dto.auth.CreateUserRequest;
import com.gigateam.cardealershipsystemapi.common.dto.user.UserDto;
import com.gigateam.cardealershipsystemapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController extends AbstractController {

  private final UserService userService;

  @GetMapping("/users/{id}")
  @Operation(
          tags = {"USER"},
          summary = "Endpoint to retrieve user by id",
          responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(useReturnTypeSchema = true)}
  )
  public ResponseEntity<ApiResponse<UserDto>> getUserById(@PathVariable("id") Long id) {
    log.info("Request on retrieving user by id. Id: {}", id);

    return userService.getUserById(id)
            .map(dto -> new ResponseEntity<>(Responses.ok(dto), HttpStatus.OK))
            .orElseGet(() ->
                    new ResponseEntity<>(
                            Responses.notFound(String.format(ResponseMessages.USER_BY_ID_NOT_FOUND_FORMAT, id)),
                            HttpStatus.NOT_FOUND
                    )
            );
  }

  @PostMapping("/users")
  @Operation(
          tags = {"USER"},
          summary = "Endpoint to create user",
          requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = {@Content(schema = @Schema(implementation = UserDto.class))}),
          responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(useReturnTypeSchema = true)}
  )
  public ResponseEntity<ApiResponse<Long>> createUser(@RequestBody UserDto dto) {
    log.info("Request on creating user. User: {}", dto);

    Long newId = userService.createUser(dto);

    return new ResponseEntity<>(Responses.created(newId), HttpStatus.CREATED);
  }

  @PutMapping("/users/{id}")
  @Operation(
          tags = {"USER"},
          summary = "Endpoint to update user",
          requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = {@Content(schema = @Schema(implementation = UserDto.class))}),
          responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(useReturnTypeSchema = true)}
  )
  public ApiResponse<Long> updateUser(@PathVariable("id") Long id, @RequestBody UserDto dto) {
    log.info("Request on updating user. User id: {}, new data: {}", id, dto);

    Long updatedId = userService.updateUser(id, dto);

    return Responses.ok(updatedId);
  }

  @DeleteMapping("/users/{id}")
  @Operation(
          tags = {"USER"},
          summary = "Endpoint to delete user by id",
          responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(useReturnTypeSchema = true)}
  )
  public ResponseEntity<ApiResponse<Void>> deleteUserById(@PathVariable("id") Long id) {
    log.info("Request on deleting user by id. Id: {}", id);

    return userService.deleteUserById(id)
            ? new ResponseEntity<>(Responses.noContent(), HttpStatus.NO_CONTENT)
            : new ResponseEntity<>(Responses.notFound(), HttpStatus.NOT_FOUND);
  }

  @PostMapping("/users/managers")
  @Operation(
      tags = {"USER"},
      summary = "Endpoint to create manager",
      requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = {@Content(schema = @Schema(implementation = CreateUserRequest.class))}),
      responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(useReturnTypeSchema = true)}
  )
  public ApiResponse<Void> createManager(@RequestBody CreateUserRequest request) {
    log.info("Request on creating manager. Request: {}", request);

    userService.createManager(request);

    return Responses.created();
  }

}
