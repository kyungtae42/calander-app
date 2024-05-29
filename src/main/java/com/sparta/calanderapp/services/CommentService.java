package com.sparta.calanderapp.services;

import com.sparta.calanderapp.dto.CalanderResponseDTO;
import com.sparta.calanderapp.dto.CommentRequestDTO;
import com.sparta.calanderapp.dto.CommentResponseDTO;
import com.sparta.calanderapp.model.Calander;
import com.sparta.calanderapp.model.Comment;
import com.sparta.calanderapp.repository.CalanderRepository;
import com.sparta.calanderapp.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
