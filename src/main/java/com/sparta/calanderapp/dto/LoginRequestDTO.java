package com.sparta.calanderapp.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequestDTO {
    private String username;
    private String password;
}