package com.sparta.calanderapp.controller;

import com.sparta.calanderapp.dto.SignupRequestDto;
import com.sparta.calanderapp.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/signup")
    private String getSignUp() {
        return "signup";
    }
    @PostMapping("/signup")
    private String createUser(@RequestBody SignupRequestDto requestDto, HttpServletResponse response) {
        userService.signup(requestDto);
        return "회원가입 완료" + response.getStatus();
    }
}
