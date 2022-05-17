package com.example.ukeesstest.config.security.filter;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        if (request.getServletPath().equals("/api/auth/signIn") || request.getServletPath().equals("/api/auth/signUp")) {
            filterChain.doFilter(request, response);
        } else if(jwtUtils.verifyToken(request.getHeader(AUTHORIZATION), response)) {
                filterChain.doFilter(request, response);
            }

    }
}
