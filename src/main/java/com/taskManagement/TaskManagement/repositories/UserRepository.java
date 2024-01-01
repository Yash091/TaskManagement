package com.taskManagement.TaskManagement.repositories;

import com.taskManagement.TaskManagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    Optional<User> findByEmail(String email);
    Boolean existsByUsername(String username);

    Optional<User> findById(String userId);

    void deleteById(String userId);

    boolean existsById(String userId);
}
