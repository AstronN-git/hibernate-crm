package com.max.service;

import com.max.entity.User;
import java.util.List;

public interface UserService {
    List<User> getUsers ();
    void saveUser (User user);
    void deleteUser (User user);
    User getUserByID (int id);
    User findByLogin(String login);
}
