package com.example.ukeesstest.dao.authUser;

import com.example.ukeesstest.domain.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserService implements UserDetailsService {
    private final AuthUserDao authUserDao;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return authUserDao.findByUsername(username).orElse(null);
    }

    public AuthUser saveUser(AuthUser user) {
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        authUserDao.save(user);
        return authUserDao.findByUsername(user.getUsername()).orElse(null);
    }
}
