package com.taskManagement.TaskManagement.services.impl;

import com.taskManagement.TaskManagement.entities.Task;
import com.taskManagement.TaskManagement.exception.CustomException;
import com.taskManagement.TaskManagement.repositories.TaskRepository;
import com.taskManagement.TaskManagement.services.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> getTasksByUserId(String userId) {
        return taskRepository.findByUserId(userId);
    }

    @Override
    public Optional<Task> getTaskById(String taskId) {
        return taskRepository.findById(taskId);
    }

    @Override
    public Task createTask(Task task) {
        task.setId(UUID.randomUUID().toString());
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(String taskId, Task updatedTask) {
        Task existingTask = taskRepository.findById(taskId).orElse(null);
        if (existingTask != null) {
            // Update task properties
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setStatus(updatedTask.getStatus());
            return taskRepository.save(existingTask);
        } else {
            throw new CustomException("Task not found with Id: " + taskId);
        }
    }
    @Override
    @Transactional
    public void deleteTask(String  taskId) {
        if(!taskRepository.existsById(taskId)) {
            throw new CustomException("Task not found with Id: " + taskId);
        }
        taskRepository.deleteById(taskId);
    }
}
