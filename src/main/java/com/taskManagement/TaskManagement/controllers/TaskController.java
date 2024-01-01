package com.taskManagement.TaskManagement.controllers;

import com.taskManagement.TaskManagement.entities.Task;
import com.taskManagement.TaskManagement.entities.User;
import com.taskManagement.TaskManagement.services.TaskService;
import com.taskManagement.TaskManagement.services.UserService;
import com.taskManagement.TaskManagement.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @Autowired
    private Validator validator;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Task>> getTaskByUserId(@PathVariable String userId) {
        List<Task> tasks = taskService.getTasksByUserId(userId);
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        try{
            validator.validate(task);
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User currentUser = userService.getUserByUsername(username);

            if (currentUser != null) {
                task.setUser(currentUser);
                Task createdTask = taskService.createTask(task);
                return new ResponseEntity<Task>(createdTask, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<Optional<Task>> getTaskById(@PathVariable String taskId) {
        return ResponseEntity.ok(taskService.getTaskById(taskId));
    }

    @PutMapping("/task/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable String taskId, @RequestBody Task task) {
        validator.validate(task);
        return ResponseEntity.ok(taskService.updateTask(taskId,task));
    }

    @DeleteMapping("/task/{taskId}")
    public String deleteTask(@PathVariable String taskId){
        taskService.deleteTask(taskId);
        return "Task Deleted Successfully!";
    }



}
