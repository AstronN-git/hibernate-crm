package com.max.controller;

import com.max.dto.UpdateUserRequest;
import com.max.entity.User;
import com.max.exception.AuthenticationException;
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

    @PutMapping ("/users")
    public User updateUser (@RequestBody UpdateUserRequest updateUserRequest) {
        if (!restAuthenticationService.isApiKeyValid(
                updateUserRequest.getAuthentication().getTimestamp(),
                updateUserRequest.getAuthentication().getApikey()
        )) {
            throw new AuthenticationException("Wrong authentication data");
        }

        User user = userService.getUserByID(updateUserRequest.getId());

        if (user == null) {
            throw new UserNotFoundException("id out of bounds");
        }

        if (updateUserRequest.getFirstName() != null)
            user.setFirstName(updateUserRequest.getFirstName());

        if (updateUserRequest.getLastName() != null)
            user.setLastName(updateUserRequest.getLastName());

        if (updateUserRequest.getEmail() != null)
            user.setEmail(updateUserRequest.getEmail());

        userService.saveUser(user);

        return user;
    }
}
