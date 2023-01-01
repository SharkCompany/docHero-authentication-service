package dochero.service.authenticationservice.security;

import dochero.service.authenticationservice.constant.CommonConstants;
import dochero.service.authenticationservice.dto.account.AccountDTO;
import dochero.service.authenticationservice.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

  private final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  @Value("${app.jwt.secret}")
  private String SECRET_KEY;

  public String generateAccessToken(AccountDTO user) {
    return Jwts.builder()
        .setIssuer("CodeJava")
        .setSubject("JWT Token") //--> use it for what ??
        .claim("username", user.getFullName())
        .claim("email", user.getEmail())
        .claim("id", user.getId())
//        .claim("authorities", populateAuthorities(user.get()))
        .claim("roles", user.getRoleName())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + CommonConstants.EXPIRE_DURATION))
        .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
        .compact();
  }

  public String getEmailFromJWT(String token) {
    Claims claims = Jwts.parser()
        .setSigningKey(SECRET_KEY)
        .parseClaimsJws(token)
        .getBody();
    return String.valueOf(claims.get("email"));
  }

  public String getUserIdFromJWT(String token) {
    Claims claims = Jwts.parser()
        .setSigningKey(SECRET_KEY)
        .parseClaimsJws(token)
        .getBody();
    return String.valueOf(claims.get("id"));
  }

  public String getUsernameFromJWT(String token) {
    Claims claims = Jwts.parser()
        .setSigningKey(SECRET_KEY)
        .parseClaimsJws(token)
        .getBody();
    return String.valueOf(claims.get("username"));
  }

  public String getAuthorities(String token) {
    Claims claims = Jwts.parser()
        .setSigningKey(SECRET_KEY)
        .parseClaimsJws(token)
        .getBody();
    return String.valueOf(claims.get("authorities"));
  }

  public String getRoles(String token) {
    Claims claims = Jwts.parser()
        .setSigningKey(SECRET_KEY)
        .parseClaimsJws(token)
        .getBody();
    return String.valueOf(claims.get("roles"));
  }

  private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
    Set<String> authoritiesSet = new HashSet<>();
    for (GrantedAuthority authority : collection) {
      authoritiesSet.add(authority.getAuthority());
    }
    return String.join(",", authoritiesSet);
  }

  public boolean validateToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authToken);
      return true;
    } catch (MalformedJwtException ex) {
      logger.error("Invalid JWT token");
    } catch (ExpiredJwtException ex) {
      logger.error("Expired JWT token");
    } catch (UnsupportedJwtException ex) {
      logger.error("Unsupported JWT token");
    } catch (IllegalArgumentException ex) {
      logger.error("JWT claims string is empty.");
    }
    return false;
  }

  public void validateAuthToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authToken);
    } catch (MalformedJwtException | ExpiredJwtException | UnsupportedJwtException |
             IllegalArgumentException ex) {
      throw new InvalidTokenException(ex.toString());
    }
  }

}

