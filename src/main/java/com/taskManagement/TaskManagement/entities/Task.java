package com.taskManagement.TaskManagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.taskManagement.TaskManagement.Constants;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "task_table")
public class Task {
    @Id
    @Column(name = "id")
    private String id;
    private String title;
    private String description;
    private String status = String.valueOf(Constants.Status.PENDING);

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    @JsonIgnore
    private User user;
}
