package com.max.dto;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private AuthenticationData authentication;

    private int id;
    private String firstName;
    private String lastName;
    private String email;
}
