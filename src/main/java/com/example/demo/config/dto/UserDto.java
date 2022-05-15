package com.example.demo.config.dto;

import com.example.demo.entity.Menu;
import lombok.Data;
import org.springframework.stereotype.Controller;

import java.util.List;

/*
* 登录功能！接收前端登录请求的参数
* */
@Data
@Controller
public class UserDto {
    private String username;
    private String password;
    private String nickname;
    private String token;
    private String role;
    private String faceUrl;
    private List<Menu> menus;
    private String Userid;

}
