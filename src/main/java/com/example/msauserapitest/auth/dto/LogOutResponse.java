package com.example.msauserapitest.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class LogOutResponse {
    private Boolean isSuccess;

    public static LogOutResponse from(boolean isSuccess) {
        return LogOutResponse.builder()
                .isSuccess(isSuccess)
                .build();
    }
}
