package com.max.dto;

import lombok.Data;

@Data
public class DeleteUserRequest {
    private AuthenticationData authentication;
    private int id;
}
