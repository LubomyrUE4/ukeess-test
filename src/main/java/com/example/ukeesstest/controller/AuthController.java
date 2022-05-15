package com.example.ukeesstest.controller;

import com.example.ukeesstest.domain.AuthUser;
import com.example.ukeesstest.payload.SignUpForm;
import com.example.ukeesstest.service.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {
    private final AuthUserService authUserService;

    @PostMapping("/signUp")
    public ResponseEntity<AuthUser> signUp(@RequestBody SignUpForm signUpForm) {
        return new ResponseEntity<>(authUserService.saveUser(new AuthUser(signUpForm.getFullName(), signUpForm.getUsername(), signUpForm.getPassword())),
                HttpStatus.CREATED);
    }
}
