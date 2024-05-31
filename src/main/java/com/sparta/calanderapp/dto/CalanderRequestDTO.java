package com.sparta.calanderapp.dto;

import com.sparta.calanderapp.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class CalanderRequestDTO {
    private Long id;
    private String title;
    private String content;
    private String password;

}
