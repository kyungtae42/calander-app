package com.sparta.calanderapp.controller;

import com.sparta.calanderapp.dto.CalanderResponseDTO;
import com.sparta.calanderapp.dto.CommentRequestDTO;
import com.sparta.calanderapp.dto.CommentResponseDTO;
import com.sparta.calanderapp.services.CommentService;
import lombok.RequiredArgsConstructor;
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
}
