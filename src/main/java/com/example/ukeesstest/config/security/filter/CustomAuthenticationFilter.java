package com.example.ukeesstest.config.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.ukeesstest.dao.authUser.AuthUserService;
import com.example.ukeesstest.domain.AuthUser;
import com.example.ukeesstest.exception.DefaultException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username, password;
        try {
            Map<String, String> requestMap = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            username = requestMap.get("username");
            password = requestMap.get("password");
        } catch (IOException e) {
            throw new AuthenticationServiceException(e.getMessage(), e);
        }

        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), jwtUtils.generateToken((AuthUser)authResult.getPrincipal()));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        Map<String, String> result = new HashMap<>();
        result.put("error", "Bad credentials");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(403);
        new ObjectMapper().writeValue(response.getOutputStream(), result);
    }
}
