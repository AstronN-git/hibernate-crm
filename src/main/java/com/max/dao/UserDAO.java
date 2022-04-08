package com.max.dao;

import com.max.entity.User;

import java.util.List;

public interface UserDAO {
    List<User> getUsers ();
    void saveUser (User user);
    void deleteUser (User user);
    User getUserByID (int id);
    User findByLogin (String login);
}
