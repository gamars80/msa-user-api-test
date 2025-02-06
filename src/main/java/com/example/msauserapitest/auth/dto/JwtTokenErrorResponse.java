package com.example.msauserapitest.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class JwtTokenErrorResponse {
    private final String message;

    public static JwtTokenErrorResponse from(String message) {
        return JwtTokenErrorResponse.builder()
                .message(message)
                .build();
    }
}
