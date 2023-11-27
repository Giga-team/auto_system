package com.gigateam.cardealershipsystemapi.controller;

import com.gigateam.cardealershipsystemapi.common.dto.ApiResponse;
import com.gigateam.cardealershipsystemapi.common.dto.Responses;
import com.gigateam.cardealershipsystemapi.common.dto.auth.LoginRequest;
import com.gigateam.cardealershipsystemapi.common.dto.auth.LoginResponse;
import com.gigateam.cardealershipsystemapi.common.dto.auth.CreateUserRequest;
import com.gigateam.cardealershipsystemapi.domain.UserRole;
import com.gigateam.cardealershipsystemapi.exception.UnauthorizedException;
import com.gigateam.cardealershipsystemapi.security.DefaultUserDetails;
import com.gigateam.cardealershipsystemapi.security.JwtService;
import com.gigateam.cardealershipsystemapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController extends AbstractController {

  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final UserService userService;

  @PostMapping("/auth/login")
  @Operation(
      tags = {"AUTH"},
      summary = "Endpoint to login",
      requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = {@Content(schema = @Schema(implementation = LoginRequest.class))}),
      responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(useReturnTypeSchema = true)}
  )
  public ApiResponse<LoginResponse> login(@RequestBody LoginRequest request) {
    log.info("Request on login. Email: {}", request.getEmail());

    try {
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
      );

      SecurityContextHolder.getContext().setAuthentication(authentication);
      DefaultUserDetails userDetails = (DefaultUserDetails) authentication.getPrincipal();
      String jwt = jwtService.generateToken(userDetails);
      UserRole role = userDetails.getAuthorities().stream()
          .map(item -> UserRole.valueOf(item.getAuthority()))
          .findFirst()
          .orElseThrow();

      return Responses.ok(new LoginResponse(userDetails, role, jwt));
    } catch (BadCredentialsException e) {
      throw new UnauthorizedException("Bad credentials provided");
    }
  }

  @PostMapping("/auth/sign-up")
  @Operation(
      tags = {"AUTH"},
      summary = "Endpoint to sign-up",
      requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = {@Content(schema = @Schema(implementation = CreateUserRequest.class))}),
      responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(useReturnTypeSchema = true)}
  )
  public ApiResponse<Void> signUp(@RequestBody CreateUserRequest request) {
    log.info("Request on sign-up. Request: {}", request);

    userService.createUser(request);

    return Responses.created();
  }

}
