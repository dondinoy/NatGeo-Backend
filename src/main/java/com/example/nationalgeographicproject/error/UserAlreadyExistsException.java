package com.example.nationalgeographicproject.error;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAlreadyExistsException extends BlogException {
    public UserAlreadyExistsException(String username, String email) {
        super("User with email: " + email + " username " + username + " already exists");
    }
}