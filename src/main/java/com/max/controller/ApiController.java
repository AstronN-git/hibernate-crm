package com.max.controller;

import com.max.dto.UpdateUserRequest;
import com.max.entity.User;
import com.max.exception.UserNotFoundException;
import com.max.service.RestAuthenticationService;
import com.max.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ApiController {
    UserService userService;
    RestAuthenticationService restAuthenticationService;

    public ApiController(UserService userService, RestAuthenticationService restAuthenticationService) {
        this.userService = userService;
        this.restAuthenticationService = restAuthenticationService;
    }

    @GetMapping("/users")
    public List<User> listUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public User findUserById(@PathVariable int id) {
        User user = userService.getUserByID(id);

        if (user == null) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }

        return user;
    }

    @PostMapping("/users")
    public Boolean updateUser (@RequestBody UpdateUserRequest updateUserRequest) {
        return restAuthenticationService.isApiKeyValid(
                updateUserRequest.getAuthentication().getTimestamp(),
                updateUserRequest.getAuthentication().getApikey()
        );
    }
}
