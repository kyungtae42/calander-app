package com.sparta.calanderapp.entity;

import com.sparta.calanderapp.dto.CommentRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Comment")
@NoArgsConstructor
public class Comment extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "calander_id", nullable = false)
    private Calander calander;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "user_id", nullable = false)
    private String userId;

    public Comment(CommentRequestDTO requestDTO, Calander calander) {
        this.content = requestDTO.getContent();
        this.userId = requestDTO.getUserId();
        this.calander = calander;
    }

    public void update(CommentRequestDTO requestDTO) {
        this.content = requestDTO.getContent();
    }
}
