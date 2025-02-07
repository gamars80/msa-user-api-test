package com.example.msauserapitest.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


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
