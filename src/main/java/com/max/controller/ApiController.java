package com.max.controller;

import com.max.dto.CreateUserRequest;
import com.max.dto.DeleteUserRequest;
import com.max.dto.UpdateUserRequest;
import com.max.entity.User;
import com.max.exception.AuthenticationException;
import com.max.exception.UserExistsException;
import com.max.exception.UserNotFoundException;
import com.max.service.RestAuthenticationService;
import com.max.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ApiController {
    private final UserService userService;
    private final RestAuthenticationService restAuthenticationService;
    private final PasswordEncoder passwordEncoder;

    public ApiController(UserService userService, RestAuthenticationService restAuthenticationService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.restAuthenticationService = restAuthenticationService;
        this.passwordEncoder = passwordEncoder;
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
            throw new AuthenticationException("Invalid authentication data");
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

    @DeleteMapping("/users")
    public String deleteUser (@RequestBody DeleteUserRequest deleteUserRequest) {
        if (!restAuthenticationService.isApiKeyValid(
                deleteUserRequest.getAuthentication().getTimestamp(),
                deleteUserRequest.getAuthentication().getApikey()
        )) {
            throw new AuthenticationException("Invalid authentication data");
        }

        User user = userService.getUserByID(deleteUserRequest.getId());

        if (user == null)
            throw new UserNotFoundException("id out of bounds");

        userService.deleteUser(user);

        return "ok";
    }

    @PostMapping("/users")
    public User createUser (@RequestBody CreateUserRequest createUserRequest) {
        if (!restAuthenticationService.isApiKeyValid(
                createUserRequest.getAuthentication().getTimestamp(),
                createUserRequest.getAuthentication().getApikey()
        )) {
            throw new AuthenticationException("Invalid authentication data");
        }

        User userWithThisLogin = userService.findByLogin(createUserRequest.getLogin());

        if (userWithThisLogin != null) {
            throw new UserExistsException("User with this login already exists");
        }

        User user = new User();

        user.setLogin(createUserRequest.getLogin());
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        user.setFirstName(createUserRequest.getFirstName());
        user.setLastName(createUserRequest.getLastName());
        user.setEmail(createUserRequest.getEmail());

        userService.saveUser(user);

        return user;
    }
}
