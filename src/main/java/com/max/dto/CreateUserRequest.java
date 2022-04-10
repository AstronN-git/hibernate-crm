package com.max.dto;

import lombok.Data;

@Data
public class CreateUserRequest {
    private AuthenticationData authentication;

    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
}
