package com.example.ukeesstest.controller;

import com.example.ukeesstest.dao.authUser.AuthUserService;
import com.example.ukeesstest.domain.AuthUser;
import com.example.ukeesstest.exception.DefaultException;
import com.example.ukeesstest.payload.SignUpForm;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {
    private final AuthUserService authUserService;

    @PostMapping("/signIn")
    @ApiOperation("Sign In")
    public void signIn() {}

    @PostMapping("/signUp")
    @ApiOperation("Sign Up")
    public ResponseEntity<AuthUser> signUp(@RequestBody @Validated SignUpForm signUpForm) throws DefaultException {
        if(authUserService.loadUserByUsername(signUpForm.getUsername()) != null) {
            throw new DefaultException("Username is already taken");
        }
        return new ResponseEntity<>(authUserService.saveUser(new AuthUser(signUpForm.getUsername(), signUpForm.getPassword())),
                HttpStatus.CREATED);
    }
}
