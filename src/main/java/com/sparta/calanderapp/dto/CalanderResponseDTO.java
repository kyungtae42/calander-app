package com.sparta.calanderapp.dto;

import com.sparta.calanderapp.model.Calander;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Getter
public class CalanderResponseDTO {
    private Long id;
    private String title;
    private String content;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String password;

    public CalanderResponseDTO(Calander calander) {
        this.id = calander.getId();
        this.title = calander.getTitle();
        this.content = calander.getContent();
        this.name = calander.getName();
        this.date = calander.getDate();
        this.password = calander.getPassword();
    }

    public CalanderResponseDTO(Long id, String title, String content, String name, Date date, String password) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.name = name;
        this.date = date;
        this.password = password;
    }
}
