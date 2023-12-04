package com.gigateam.cardealershipsystemapi.security;

import com.gigateam.cardealershipsystemapi.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

  private static final String BEARER_PREFIX = "Bearer ";

  private final JwtService jwtService;
  private final UserService userDetailsService;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain
  ) throws ServletException, IOException {
    String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (isAuthHeaderAbsent(authHeader)) {
      filterChain.doFilter(request, response);
      return;
    }

    String jwt = retrieveToken(authHeader);

    if (!jwtService.validate(jwt)) { //TODO: mb another validation?
      filterChain.doFilter(request, response);
      return;
    }

    String email = jwtService.extractUsername(jwt);

    UserDetails userDetails = userDetailsService
        .getUserByEmail(email)
        .orElse(null);

    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
        userDetails,
        null,
        Objects.isNull(userDetails) ? Collections.emptySet() : userDetails.getAuthorities()
    );

    authToken.setDetails(
       new WebAuthenticationDetailsSource().buildDetails(request)
    );

    SecurityContextHolder.getContext().setAuthentication(authToken);
    filterChain.doFilter(request, response);
  }

  private boolean isAuthHeaderAbsent(String authHeader) {
    return StringUtils.isEmpty(authHeader) || !authHeader.startsWith(BEARER_PREFIX);
  }

  private String retrieveToken(String authHeader) {
    return authHeader.split(" ")[1].trim();
  }

}
