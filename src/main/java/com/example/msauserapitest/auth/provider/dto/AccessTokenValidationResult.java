package com.example.msauserapitest.auth.provider.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
public class AccessTokenValidationResult {
    private final Boolean validated;
    private final String failedMessage;

    public static AccessTokenValidationResult success() {
        return new AccessTokenValidationResult(true, null);
    }

    public static AccessTokenValidationResult fail(@NotBlank String failedMessage) {
        return new AccessTokenValidationResult(false, failedMessage);
    }

    public boolean isFailed() {
        return !validated;
    }

    public String getFailedMessage() {
        return failedMessage;
    }
}
