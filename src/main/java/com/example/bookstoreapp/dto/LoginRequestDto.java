package com.example.bookstoreapp.dto;

import com.example.bookstoreapp.enums.AuthenticationType;
import org.jetbrains.annotations.NotNull;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotEmpty;

@Getter
@Setter
public class LoginRequestDto {

    public LoginRequestDto(@NotNull String email, @NotNull String password) {
        this.email = email;
        this.password = password;
    }
    public LoginRequestDto(){

    }


    @NotEmpty(message = "email cant be empty")
    private String email;



    @NotEmpty(message = "password cant be empty")
    private String password;

    private AuthenticationType type;

}