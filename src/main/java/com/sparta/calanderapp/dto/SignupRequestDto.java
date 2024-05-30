package com.sparta.calanderapp.dto;

import com.sparta.calanderapp.entity.UserRoleEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @NotBlank
    private String nickname;
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    private boolean admin = false;
    private String adminToken = "";
}
