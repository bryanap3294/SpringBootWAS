package com.bryan.springbootwas.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class TokenAuthenticationService {

  private static final Logger LOGGER = LoggerFactory.getLogger(TokenAuthenticationService.class);

  static final long EXPIRATIONTIME = 1800000; // 30 minutos
  static final String SECRET = "ThisIsASecret";
  static final String TOKEN_PREFIX = "Bearer";
  static final String HEADER_STRING = "Authorization";

  static void addAuthentication(HttpServletResponse res, Authentication authentication) {

    String version = authentication.getName();
    switch (version){
      case "1":
        addAuthenticationV1(res, authentication);
        break;
      case "2":
        addAuthenticationV2(res, authentication);
        break;
      default:
        break;
    }
  }

  static Authentication getAuthentication(HttpServletRequest request) {
    String token = request.getHeader(HEADER_STRING);
    Authentication authentication = null;
    if (token != null) {
      Claims claims = Jwts.parser()
              .setSigningKey(SECRET)
              .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
              .getBody();
      String version= (String) claims.get("loginVersion");
      switch (version){
        case "1":
          authentication = getAuthenticationV1(request);
          break;
        case "2":
          authentication = getAuthenticationV2(request);
          break;
        default:
          break;
      }
    }
    return authentication;
  }

  static void addAuthenticationV1(HttpServletResponse res, Authentication authentication) {
    String version = authentication.getName();
    String JWT = Jwts.builder()
            .setSubject(version)
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
            .signWith(SignatureAlgorithm.HS512, SECRET)
            .compact();
    res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
  }

  static void addAuthenticationV2(HttpServletResponse res, Authentication authentication) {
    String version = authentication.getName();
    String JWT = Jwts.builder()
            .setSubject(version)
            .claim("loginVersion", version)
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
            .signWith(SignatureAlgorithm.HS512, SECRET)
            .compact();
    res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
  }

  static Authentication getAuthenticationV1(HttpServletRequest request) {
    String token = request.getHeader(HEADER_STRING);
    if (token != null) {
      // parse the token.
      String user = Jwts.parser()
              .setSigningKey(SECRET)
              .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
              .getBody()
              .getSubject();
      List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

      Claims claims = Jwts.parser()
              .setSigningKey(SECRET)
              .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
              .getBody();
      if(user != null){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, grantedAuthorityList);
        usernamePasswordAuthenticationToken.setDetails(null);
        return usernamePasswordAuthenticationToken;
      }else{
        return null;
      }
    }
    return null;
  }

  static Authentication getAuthenticationV2(HttpServletRequest request) {
    String token = request.getHeader(HEADER_STRING);
    if (token != null) {
      // parse the token.
      String user = Jwts.parser()
              .setSigningKey(SECRET)
              .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
              .getBody()
              .getSubject();
      List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

      Claims claims = Jwts.parser()
              .setSigningKey(SECRET)
              .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
              .getBody();

      if(user != null){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, grantedAuthorityList);
        usernamePasswordAuthenticationToken.setDetails(null);
        return usernamePasswordAuthenticationToken;
      }else{
        return null;
      }
    }
    return null;
  }

}