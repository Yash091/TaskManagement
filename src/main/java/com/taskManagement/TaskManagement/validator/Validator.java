package com.taskManagement.TaskManagement.validator;

import com.taskManagement.TaskManagement.entities.JwtRequest;
import com.taskManagement.TaskManagement.entities.Task;
import com.taskManagement.TaskManagement.entities.User;
import lombok.Data;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@Component
public class Validator {
    private static final String GMAIL_REGEX = "^[a-zA-Z0-9_]+(\\.[a-zA-Z0-9_]+)*@gmail\\.com$";
    private static boolean isValidGmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile(GMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
    public void validate(Task task){
        if(task.getTitle().isEmpty() || task.getDescription().isEmpty()) {
            throw new BadCredentialsException("Empty fields are not allowed!!");
        }
    }
    public void validate(User user) {
        if(user.getUsername().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
            throw new BadCredentialsException("Empty fields are not allowed!!");
        }
        if(isValidGmail(user.getEmail())) {
            throw new BadCredentialsException("Invalid email !!");
        }
    }
    public void validate(JwtRequest request) {
        if(request.getUsername().isEmpty() || request.getPassword().isEmpty()) {
            throw new BadCredentialsException("Empty fields are not allowed!!");
        }
    }
}
