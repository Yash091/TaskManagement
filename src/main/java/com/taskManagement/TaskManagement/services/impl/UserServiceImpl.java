package com.taskManagement.TaskManagement.services.impl;

import com.taskManagement.TaskManagement.entities.User;
import com.taskManagement.TaskManagement.exception.CustomException;
import com.taskManagement.TaskManagement.repositories.UserRepository;
import com.taskManagement.TaskManagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        if(userRepository.existsByUsername(user.getUsername())) {
            throw new CustomException("Username does not exists. Try another username.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
