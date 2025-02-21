package com.example.msauserapitest.auth.dto;

import com.example.msauserapitest.auth.provider.dto.AccessToken;
import com.example.msauserapitest.user.service.dto.RefreshTokenDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AdminTokenIssueResponse {
    private final String accessToken;
    private final String refreshToken;
    private final Long userId;

    @Builder
    public AdminTokenIssueResponse(AccessToken accessToken, RefreshTokenDto refreshToken, Long userId) {
        this.accessToken = accessToken.getValue();
        this.refreshToken = refreshToken.getValue();
        this.userId = userId;
    }

    public static AdminTokenIssueResponse from(AccessToken accessToken, RefreshTokenDto refreshToken, Long userId) {
        return new AdminTokenIssueResponse(accessToken, refreshToken, userId);
    }
}
