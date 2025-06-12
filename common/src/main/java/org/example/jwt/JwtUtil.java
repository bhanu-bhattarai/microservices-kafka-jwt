package org.example.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.security.Key;

public class JwtUtil {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // generate key
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public static String validateTokenAndGetUsername(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException e) {
            return null; // invalid token
        }
    }
}
