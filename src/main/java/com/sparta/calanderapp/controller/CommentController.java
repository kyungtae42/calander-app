package com.sparta.calanderapp.controller;

import com.sparta.calanderapp.dto.CommentRequestDTO;
import com.sparta.calanderapp.dto.CommentResponseDTO;
import com.sparta.calanderapp.security.UserDetailsImpl;
import com.sparta.calanderapp.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/{calanderId}")
    public CommentResponseDTO createComment(@RequestBody CommentRequestDTO requestDTO, @PathVariable Long calanderId) {
        return commentService.createComment(requestDTO, calanderId);
    }
    @GetMapping("/{calanderId}")
    public List<CommentResponseDTO> getComments(@PathVariable Long calanderId) {
        return commentService.getComments(calanderId);
    }
    @PutMapping("/{commentId}")
    public CommentResponseDTO updateComment(@RequestBody CommentRequestDTO requestDTO, @PathVariable Long commentId) {
        return commentService.updateComment(requestDTO, commentId);
    }
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.deleteComment(commentId, userDetails.getUser());
        return new ResponseEntity<>("댓글이 성공적으로 삭제되었습니다.", HttpStatusCode.valueOf(200));
    }
}
