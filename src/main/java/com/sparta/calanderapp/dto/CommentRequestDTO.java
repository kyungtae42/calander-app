package com.sparta.calanderapp.dto;

import com.sparta.calanderapp.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDTO {
    private Long id;
    private String content;
    private User user;
}
