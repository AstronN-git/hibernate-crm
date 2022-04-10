package com.max.controller;

import com.max.entity.User;
import com.max.exception.UserNotFoundException;
import com.max.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ApiController {
    UserService userService;

    public ApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/list")
    public List<User> listUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/id/{id}")
    public User findUserById(@PathVariable int id) {
        User user = userService.getUserByID(id);

        if (user == null) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }

        return user;
    }
}
