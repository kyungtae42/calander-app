package com.sparta.calanderapp.dto;

import com.sparta.calanderapp.model.Calander;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
public class CalanderResponseDTO {
    private Long id;
    private String title;
    private String content;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String password;

    public CalanderResponseDTO(Calander calander) {
        this.id = calander.getId();
        this.title = calander.getTitle();
        this.content = calander.getContent();
        this.name = calander.getName();
        this.password = calander.getPassword();
        this.createdAt = calander.getCreatedAt();
        this.updatedAt = calander.getUpdatedAt();
    }

    public void update (Long id, String title, String content, String name, Date date, String password) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.name = name;
        this.password = password;
    }
}
