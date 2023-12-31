package com.taskManagement.TaskManagement;

import org.springframework.stereotype.Component;

@Component
public class Constants {
    public enum Status {
        ACTIVE("Active"),
        COMPLETED("Completed"),
        PENDING("Pending");

        Status(String value) {
        }
    }
}
