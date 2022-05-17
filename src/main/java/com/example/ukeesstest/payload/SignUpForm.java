package com.example.ukeesstest.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignUpForm {
    @NotBlank(message = "Username should not be blank")
    @Size(min=3, message = "Username should have at least 3 characters")
    @Size(max=20, message = "Username should have not more than 20 characters")
    private String username;
    @NotBlank(message = "Password should not be blank")
    @Size(min=3, message = "Password should have at least 3 characters")
    @Size(max=20, message = "Password should have not more than 20 characters")
    private String password;
}
