package com.example.ukeesstest.repo;

import com.example.ukeesstest.domain.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserRepo extends JpaRepository<AuthUser, Long> {
    AuthUser findAuthUserByUsername(String username);
}
