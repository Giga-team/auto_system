package com.gigateam.cardealershipsystemapi.security;

import com.gigateam.cardealershipsystemapi.domain.User;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class DefaultUserDetails implements UserDetails {

  private Long id;
  private String name;
  private String surname;
  private String phoneNumber;
  private String email;
  private String password;
  private Set<? extends GrantedAuthority> authorities = new HashSet<>();

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
