package com.example.sensor_api.config;

import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.JwtException;
@Component
public class JwtUtil {
    private final String SECRET_KEY = "secretsecretsecretsecretsecretsecret"; // Минимум 256 бит для HS256
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // Генерация токена
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 час
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Извлечение имени пользователя
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Валидация токена
    public boolean validateToken(String token, String username) {
        try {
            String extractedUsername = extractUsername(token);
            return extractedUsername.equals(username) && !isTokenExpired(token);
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // Проверка на истечение токена
    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
}
