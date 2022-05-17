package com.example.ukeesstest.dao.authUser;

import com.example.ukeesstest.domain.AuthUser;

import java.util.List;
import java.util.Optional;

public interface AuthUserDao {
    List<AuthUser> findAll();
    AuthUser save(AuthUser authUser);
    Long deleteById(Long id);
    Optional<AuthUser> findById(Long id);
    AuthUser update(AuthUser authUser);
    Optional<AuthUser> findByUsername(String userName);
}
