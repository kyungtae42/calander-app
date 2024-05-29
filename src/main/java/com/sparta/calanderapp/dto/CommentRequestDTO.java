package com.sparta.calanderapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDTO {
    private Long id;
    private String content;
    private String userId;
}
