package com.example.microusuarios.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;  // Importa esta clase para generar la clave secreta


import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;



import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtUtilService {
  // Genera una clave secreta segura
	
  private final static String secret = "QJeKx+s7XIv1WbBlj7vJ9CD3Ozj1rB3qjlNZY9ofWKJSaBNBo5r1q9Rru/OWlYb+UHV1n4/LJl1OBYYZZ7rhJEnn5peyHCd+eLJfRdArE37pc+QDIsJlabQtR7tYRa+SnvGRyL01uZsK33+gezV+/GPXBnPTj8fOojDUzJiPAvE=";
  private static final byte[] SECRET_KEY_BYTES = Base64.getDecoder().decode(secret);

  public static final long JWT_TOKEN_VALIDITY = 1000 * 60 * 60 * 60; // 8 Horas

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    return claimsResolver.apply(extractAllClaims(token));
  }

 /* private Claims extractAllClaims(String token) {
    return Jwts.parserBuilder()
    .setSigningKey(SECRET_KEY_BYTES)
    .build()
    .parseClaimsJws(token)
    .getBody();
  }*/
  
  private Claims extractAllClaims(String token) {
	    return Jwts.parserBuilder()
	            .setSigningKey(SECRET_KEY_BYTES)
	            .setAllowedClockSkewSeconds(60000000) // Tolerancia de reloj de 60 segundos
	            .build()
	            .parseClaimsJws(token)
	            .getBody();
	}


 /* private Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }
  */
  private Boolean isTokenExpired(String token) {
	    Date expirationDate = extractExpiration(token);
	    Date currentDate = new Date();

	    // Agrega un margen de tiempo (tolerancia de reloj) de, por ejemplo, 5 minutos
	    long clockToleranceMillis = 1000 * 60 * 60 * (long) 8; // 5 minutos en milisegundos

	    return expirationDate.before(new Date(currentDate.getTime() + clockToleranceMillis));
	}


  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    // Agregando información adicional como "claim"
    var rol = userDetails.getAuthorities().stream().collect(Collectors.toList()).get(0);
    claims.put("rol", rol);
    return createToken(claims, userDetails.getUsername());
  }

  private String createToken(Map<String, Object> claims, String subject) {

    return Jwts
        .builder()
        .setClaims(claims)
        .setSubject(subject)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
        .signWith(Keys.hmacShaKeyFor(SECRET_KEY_BYTES), SignatureAlgorithm.HS256)
        .compact();
  }

  public boolean validateToken(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }
}


