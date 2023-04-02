package com.electricitysystem.jwt;


import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    @Value("${SECRET_KEY}")
    private String SECRET_KEY;
    @Value("${EXPIRE_TIME}")
    private long EXPIRE_TIME;
    private static final Logger logger = LoggerFactory.getLogger(JwtService.class.getName());

    public String generateTokenLogin(Authentication authentication) {
        try {
            UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
            System.err.println(userPrincipal);
            return Jwts.builder()
                    .setSubject((userPrincipal.getUsername()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date((new Date()).getTime() + EXPIRE_TIME * 100000))
                    .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                    .compact();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME * 1000)).signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {

            logger.error("Invalid JWT signature -> Message: {} ", e);

        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e);

        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e);

        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);

        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);

        }
        return false;
    }


    public String getUserNameFromJwtToken(String token) {

        String userName = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody().getSubject();
        return userName;
    }
}