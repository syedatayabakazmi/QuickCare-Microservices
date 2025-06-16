package com.quickcare.auth_service.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtUtil {

    private final String SECRET = "my-super-secret-key-which-is-long";

    public String generateToken(String username, String role) {
        return JWT.create()
                .withSubject(username)
                .withClaim("role", role)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .sign(Algorithm.HMAC256(SECRET));
    }

    public String extractUsername(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET)).build()
                .verify(token).getSubject();
    }

    public String extractRole(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET)).build()
                .verify(token).getClaim("role").asString();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return extractUsername(token).equals(userDetails.getUsername());
    }
}
