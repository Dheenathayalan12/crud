package com.example.Crud.utils;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    private final String SECRET="dfwggggadhddshj123456789012345678";
    private final long EXPIRATION= 1000 * 60* 2;
    private final Key secretkey= Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION))
                .signWith(secretkey, SignatureAlgorithm.HS256)
                .compact();
    }
    public String extractemail(String token){
        return Jwts.parser()
                .setSigningKey(secretkey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public boolean validate(String token){
        try {
                extractemail(token);
            return true;
        } catch (JwtException exception) {
            return false;
        }
    }


}
