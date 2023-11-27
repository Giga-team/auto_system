package com.gigateam.cardealershipsystemapi.security;

import com.gigateam.cardealershipsystemapi.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtService {

  @Value("${jwt.secret-key}")
  private String secretKey;

  @Value("${jwt.expiration-milliseconds}")
  private Long expirationMilliseconds;

  public String generateToken(DefaultUserDetails user) {
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

  private Claims generateClaims(DefaultUserDetails user) {
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

  public boolean validate(String jwt) {
    try {
      Jwts.parser()
          .verifyWith(getSecret())
          .build()
          .parse(jwt);

      return true;
    } catch (SignatureException | MalformedJwtException e) {
      return false;
    }

  }

  private SecretKey getSecret() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
  }

  public String extractUsername(String jwt) {
    return extractClaim(jwt, Claims::getSubject);
  }

  private <T> T extractClaim(String jwt, Function<Claims, T> claimsResolver) {
    return claimsResolver.apply(extractClaims(jwt));
  }

  private Claims extractClaims(String jwt) {
    return Jwts.parser()
        .verifyWith(getSecret())
        .build()
        .parseClaimsJws(jwt)
        .getBody();
  }

}
