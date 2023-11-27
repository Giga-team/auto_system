package com.gigateam.cardealershipsystemapi.security;

import com.gigateam.cardealershipsystemapi.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtService {

  @Value("${jwt.secret-key}")
  private String secretKey;

  @Value("${jwt.expiration-milliseconds}")
  private Long expirationMilliseconds;

  public String generateToken(User user) {
    Date now = new Date();
    Date expirationDate = new Date(now.getTime() + expirationMilliseconds);

    Claims claims = generateClaims(user);
    Key key = getSigningKey();

    return Jwts.builder()
        .claims(claims)
        .issuedAt(now)
        .expiration(expirationDate)
        .signWith(key)
        .compact();
  }

  private Claims generateClaims(User user) {
    return Jwts.claims()
        .subject(user.getEmail())
        .add("id", user.getId())
        .add("name", user.getName())
        .add("surname", user.getSurname())
        .build();
  }

  private Key getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }

}
