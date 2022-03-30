package com.max.controller;

import com.max.entity.User;
import com.max.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final UserService userService;

    public CustomerController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public String listCustomers (Model model) {
        List<User> allUsers = userService.getUsers();
        model.addAttribute("users", allUsers);
        return "customer-list";
    }

    @GetMapping("/createUser")
    public String createUserForm (Model model) {
        model.addAttribute("user", new User());
        return "create-user";
    }

    @PostMapping("/saveUser")
    public String saveNewUser (@ModelAttribute User user, Model model) {
        userService.saveUser(user);
        return "redirect:/customer/list";
    }

    @PostMapping("/updateUser")
    public String updateUserForm (@ModelAttribute(name = "userId") String userId, Model model) {
        User user = userService.getUserByID(Integer.parseInt(userId));
        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("/saveUpdatedUser")
    public String saveUpdatedUser (@ModelAttribute User user, Model model) {
        userService.saveUser(user);
        return "redirect:/customer/list";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@ModelAttribute(name = "userId") String userId, Model model) {
        User user = userService.getUserByID(Integer.parseInt(userId));
        userService.deleteUser(user);
        return "redirect:/customer/list";
    }
}
