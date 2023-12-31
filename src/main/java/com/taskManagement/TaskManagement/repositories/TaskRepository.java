package com.taskManagement.TaskManagement.repositories;

import com.taskManagement.TaskManagement.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserId(String userId);

    boolean existsById(String taskId);

    void deleteById(String taskId);

    Optional<Task> findById(String taskId);
}
