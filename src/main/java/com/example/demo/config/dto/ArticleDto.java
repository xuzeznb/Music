package com.example.demo.config.dto;

import lombok.Data;
import org.springframework.stereotype.Controller;

@Data
@Controller
public class ArticleDto {
    public String ArtId;
    public String tag;

}
