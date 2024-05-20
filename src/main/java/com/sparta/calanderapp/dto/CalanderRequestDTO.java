package com.sparta.calanderapp.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class CalanderRequestDTO {
    private Long id;
    private String title;
    private String content;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String password;

    public void setDate(String date) {
        this.date = Date.valueOf(date);
    }
}
