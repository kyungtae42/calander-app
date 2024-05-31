package com.sparta.calanderapp.dto;

import com.sparta.calanderapp.entity.Calander;
import com.sparta.calanderapp.entity.User;
import lombok.Getter;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
public class CalanderResponseDTO {
    private Long id;
    private String title;
    private String content;
    private User user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String password;

    public CalanderResponseDTO(Calander calander) {
        this.id = calander.getId();
        this.title = calander.getTitle();
        this.content = calander.getContent();
        this.user = calander.getUser();
        this.password = calander.getPassword();
        this.createdAt = calander.getCreatedAt();
        this.updatedAt = calander.getUpdatedAt();
    }

    public void update (Long id, String title, String content, String password) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.password = password;
    }
}
