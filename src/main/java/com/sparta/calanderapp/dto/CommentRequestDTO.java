package com.sparta.calanderapp.dto;

import com.sparta.calanderapp.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDTO {
    private Long id;
    @NotBlank
    private String content;
}
