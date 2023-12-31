package com.taskManagement.TaskManagement.services;

import com.taskManagement.TaskManagement.entities.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

        List<Task> getTasksByUserId(String userId);

        Optional<Task> getTaskById(String taskId);

        Task createTask(Task task);

        Task updateTask(String taskId, Task updatedTask);

        void deleteTask(String taskId);
}
