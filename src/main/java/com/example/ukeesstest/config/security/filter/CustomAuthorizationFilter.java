package com.example.ukeesstest.config.security.filter;

import com.example.ukeesstest.config.security.JWT.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private List<String> excludeUrlPatterns = new ArrayList<>(Arrays.asList("/swagger-ui/", "/swagger-ui.html", "/swagger-uui.html", "/webjars/springfox-swagger-ui/springfox.css", "/webjars/springfox-swagger-ui/swagger-ui-bundle.js", "/webjars/springfox-swagger-ui/swagger-ui.css", "/webjars/springfox-swagger-ui/swagger-ui-standalone-preset.js", "/webjars/springfox-swagger-ui/springfox.js", "/swagger-resources/configuration/ui", "/webjars/springfox-swagger-ui/favicon-32x32.png", "/swagger-resources/configuration/security", "/swagger-resources", "/v2/api-docs", "/webjars/springfox-swagger-ui/fonts/titillium-web-v6-latin-700.woff2", "/webjars/springfox-swagger-ui/fonts/open-sans-v15-latin-regular.woff2", "/webjars/springfox-swagger-ui/fonts/open-sans-v15-latin-700.woff2", "/webjars/springfox-swagger-ui/favicon-16x16.png", "/swagger-resources/**", "/swagger-ui.html", "/swagger-ui/**", "/v2/api-docs", "/webjars/**", "/swagger-ui/springfox.css", "/swagger-ui/swagger-ui.css", "/swagger-ui/swagger-ui-bundle.js", "/swagger-ui/swagger-ui-standalone-preset.js", "/swagger-ui/springfox.js",
            "/swagger-ui/favicon-32x32.png", "/swagger-ui/favicon-16x16.png", "/api/auth/signIn", "/api/auth/signUp"));

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if(jwtUtils.verifyToken(request.getHeader(AUTHORIZATION), response)) {
                filterChain.doFilter(request, response);
            }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        if (excludeUrlPatterns.contains(path)) {
            return true;
        } else {
            return false;
        }
    }
}
