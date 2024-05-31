package com.sparta.calanderapp.controller;

import com.sparta.calanderapp.entity.UserRoleEnum;
import com.sparta.calanderapp.security.UserDetailsImpl;
import com.sparta.calanderapp.services.CalanderService;
import com.sparta.calanderapp.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MappingController {
    CalanderService calanderService;
    CommentService commentService;
    @GetMapping("/")
    public String getIndex(){
        return "index";
    }
    @GetMapping("/{id}")
    public String getDetail(@PathVariable Long id){
        return "detail";
    }
    @GetMapping("/update/{id}")
    public String getUpdate(@PathVariable Long id){
        return "update";
    }
}
