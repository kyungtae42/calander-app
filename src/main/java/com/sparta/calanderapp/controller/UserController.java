package com.sparta.calanderapp.controller;

import com.sparta.calanderapp.dto.LoginRequestDTO;
import com.sparta.calanderapp.dto.SignupRequestDto;
import com.sparta.calanderapp.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/login-page")
    private String getLogin() {
        return "login";
    }

    @GetMapping("/signup-page")
    private String getSignUp() {
        return "signup";
    }
    @PostMapping("/signup")
    @ResponseBody
    private String createUser(@RequestBody SignupRequestDto requestDto, HttpServletResponse response) {
        userService.signup(requestDto);
        return "회원가입 완료" + response.getStatus();
    }
    @PostMapping("/login")
    private String login(@RequestBody LoginRequestDTO requestDTO, HttpServletResponse res) {
        try {
            userService.login(requestDTO, res);
        } catch (Exception e) {
            return "redirect:/login-page?error";
        }
        return "redirect:/";
    }
}
