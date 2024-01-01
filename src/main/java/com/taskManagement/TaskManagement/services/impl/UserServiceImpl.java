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
            throw new CustomException("Username already exists. Try another username.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> getUserById(String userId) {

        Optional<User>user = Optional.ofNullable(userRepository.findById(userId).orElse(null));
        if(user.isPresent()){
            throw new CustomException("User not found!");
        }
        return user;
    }

    public User getUserByUsername(String username) {
        if(!userRepository.existsByUsername(username)) {
            throw new CustomException("User does not exists!");
        }
        return userRepository.findByUsername(username);
    }


    public void deleteUser(String userId) {
        if(!userRepository.existsById(userId)) {
            throw new CustomException("User does not exists!");
        }
        userRepository.deleteById(userId);
    }
}
