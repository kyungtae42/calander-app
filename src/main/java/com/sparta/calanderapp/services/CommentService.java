package com.sparta.calanderapp.services;

import com.sparta.calanderapp.dto.CommentRequestDTO;
import com.sparta.calanderapp.dto.CommentResponseDTO;
import com.sparta.calanderapp.entity.Calander;
import com.sparta.calanderapp.entity.Comment;
import com.sparta.calanderapp.repository.CalanderRepository;
import com.sparta.calanderapp.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CalanderRepository calanderRepository;
    public CommentResponseDTO createComment(CommentRequestDTO requestDTO, Long calanderId) {
        if(calanderId == null) {
            throw new IllegalArgumentException("올바른 일정을 입력해주세요");
        }
        Calander calander = calanderRepository.findById(calanderId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 일정입니다.")
        );
        if(requestDTO.getContent().isEmpty()) {
            throw new IllegalArgumentException("댓글을 입력하세요.");
        }
        Comment comment = new Comment(requestDTO, calander);
        commentRepository.save(comment);
        return new CommentResponseDTO(comment);
    }

    public List<CommentResponseDTO> getComments(Long calanderId) {
        Calander calander = calanderRepository.findById(calanderId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 일정입니다.")
        );
        return commentRepository.findAllByCalander(calander).stream().map(CommentResponseDTO::new).toList();
    }

    @Transactional
    public CommentResponseDTO updateComment(CommentRequestDTO requestDTO, Long commentId) {
        Comment comment = findById(commentId);
        if(requestDTO.getUserId().equals(comment.getUserId())) {
            comment.update(requestDTO);
        } else {
            throw new RuntimeException("사용자가 일치하지 않습니다.");
        }
        return new CommentResponseDTO(comment);
    }

    public Long deleteComment(Long commentId, String userId) {
        Comment comment = findById(commentId);
        if(comment.getUserId().equals(userId)) {
            commentRepository.delete(comment);
        } else {
            throw new RuntimeException("사용자가 일치하지 않습니다.");
        }
        return commentId;
    }

    public Comment findById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 댓글입니다.")
        );
    }
}
