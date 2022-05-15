package com.example.ukeesstest.service;

import com.example.ukeesstest.domain.AuthUser;
import com.example.ukeesstest.repo.AuthUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserService implements UserDetailsService {
    private final AuthUserRepo authUserRepo;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return authUserRepo.findAuthUserByUsername(username);
    }

    public AuthUser saveUser(AuthUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return authUserRepo.save(user);
    }
}