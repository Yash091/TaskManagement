package com.taskManagement.TaskManagement.controllers;

import com.taskManagement.TaskManagement.entities.User;
import com.taskManagement.TaskManagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5173")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> create_user(@RequestBody User user) {
        user.setId(UUID.randomUUID().toString());
        User user1 = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }
}
