package com.taskManagement.TaskManagement.services.impl;

import com.taskManagement.TaskManagement.entities.User;
import com.taskManagement.TaskManagement.exception.CustomException;
import com.taskManagement.TaskManagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if(!userRepository.existsByUsername(username)){
            throw new CustomException("User does not exists!!");
        }
        User user = userRepository.findByUsername(username);
        return user;
    }
}
