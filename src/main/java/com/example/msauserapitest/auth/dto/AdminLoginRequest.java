package com.example.msauserapitest.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@NoArgsConstructor
public class AdminLoginRequest {
    @NotBlank
    private String loginId;
    @NotBlank
    private String password;

    public AdminLoginRequest(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
