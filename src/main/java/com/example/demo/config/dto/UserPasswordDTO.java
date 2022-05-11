package com.example.demo.config.dto;

import lombok.Data;
import org.springframework.context.annotation.Bean;

@Data

public class UserPasswordDTO {
    private String username;
    private String password;
    private String newPassword;
}
