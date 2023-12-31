package com.taskManagement.TaskManagement.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponse {
    private String jwtToken;
    private String username;
    private String id;
}
