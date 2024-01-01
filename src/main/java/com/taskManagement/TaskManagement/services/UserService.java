package com.taskManagement.TaskManagement.services;

import com.taskManagement.TaskManagement.entities.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(String userId);

    User getUserByUsername(String username);

    User registerUser(User user);

    void deleteUser(String userId);
}
