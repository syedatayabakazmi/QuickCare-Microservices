package com.quickcare.api_gateway.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private final String SECRET = "my-super-secret-key-which-is-long"; // Must match the AuthService

    public DecodedJWT validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            return verifier.verify(token); // returns decoded JWT if valid
        } catch (JWTVerificationException ex) {
            return null; // invalid token
        }
    }
}
