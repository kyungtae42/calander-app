package com.sparta.calanderapp.entity;

import com.sparta.calanderapp.dto.CalanderRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "calander")
@NoArgsConstructor
public class Calander extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "calander")
    private List<Comment> comments;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "title", nullable = false)
    @NotBlank
    private String title;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "password", nullable = false, length = 10)
    private String password;

    public Calander(CalanderRequestDTO requestDTO, User user) {
        this.title = requestDTO.getTitle();
        this.content = requestDTO.getContent();
        this.password = requestDTO.getPassword();
        this.user = user;
    }

    public void update(CalanderRequestDTO requestDTO) {
        this.title = requestDTO.getTitle();
        this.content = requestDTO.getContent();
    }
}
