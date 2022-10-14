package com.example.rest;

import com.example.rest.entities.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String fullname;

    private String state;

    private String phone;

    private double balance;
    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(
                username, passwordEncoder.encode(password),
                fullname,  state, phone,balance);
    }
}