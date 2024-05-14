package com.sparta.calanderapp.model;

import com.sparta.calanderapp.dto.CalanderRequestDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class Calander {
    private Long id;
    private String title;
    private String content;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String password;

    public Calander(CalanderRequestDTO requestDTO) {
        this.title = requestDTO.getTitle();
        this.content = requestDTO.getContent();
        this.name = requestDTO.getName();
        this.date = requestDTO.getDate();
        this.password = requestDTO.getPassword();
    }
}
