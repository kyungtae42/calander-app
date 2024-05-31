package com.sparta.calanderapp.dto;

import com.sparta.calanderapp.entity.Comment;
import com.sparta.calanderapp.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDTO {
    private Long id;
    private String content;
    private User user;
    private Long calander_id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CommentResponseDTO(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.user = comment.getUser();
        this.calander_id = comment.getCalander().getId();
        this.createdAt = comment.getCreatedAt();
        this.updatedAt = comment.getUpdatedAt();
    }
}
