package com.sparta.calanderapp.model;

import com.sparta.calanderapp.dto.CalanderRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "calander")
@NoArgsConstructor
public class Calander extends TimeStamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "name", nullable = false, length = 20)
    private String name;
    @Column(name = "password", nullable = false, length = 10)
    private String password;

    public Calander(CalanderRequestDTO requestDTO) {
        this.title = requestDTO.getTitle();
        this.content = requestDTO.getContent();
        this.name = requestDTO.getName();
        this.password = requestDTO.getPassword();
    }

    public void update(CalanderRequestDTO requestDTO) {
        this.title = requestDTO.getTitle();
        this.content = requestDTO.getContent();
        this.name = requestDTO.getName();
    }
}
