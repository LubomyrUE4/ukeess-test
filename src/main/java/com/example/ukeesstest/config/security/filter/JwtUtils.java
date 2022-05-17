package com.example.ukeesstest.config.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.ukeesstest.domain.AuthUser;
import com.example.ukeesstest.exception.DefaultException;
import com.example.ukeesstest.exception.advice.ExceptionController;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiresAt}")
    private Long jwtExpiresAt;

    public Map<String, String> generateToken(AuthUser user) {
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());

        String token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtExpiresAt * 1000))
                .sign(algorithm);

        Map<String, String> result = new HashMap<>();
        result.put("userId", user.getUserID().toString());
        result.put("expiresAt", "" + jwtExpiresAt);
        result.put("token", token);
        return result;
    }

    public boolean verifyToken(String authorizationHeader, HttpServletResponse response) throws IOException {
        boolean isVerified = false;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String token = authorizationHeader.substring(7);
                Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(token);
                String email = decodedJWT.getSubject();
                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, null);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                isVerified = true;
            } catch (Exception e) {
                Map<String, String> result = new HashMap<>();
                result.put("error", e.getMessage());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setStatus(403);
                new ObjectMapper().writeValue(response.getOutputStream(), result);
            }
        }
        return isVerified;
    }
}
