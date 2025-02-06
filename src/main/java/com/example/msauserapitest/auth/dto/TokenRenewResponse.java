package com.example.msauserapitest.auth.dto;

import kr.co.gacha.auth.provider.dto.AccessToken;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenRenewResponse {
    private final String accessToken;

    @Builder
    public TokenRenewResponse(AccessToken accessToken) {
        this.accessToken = accessToken.getValue();
    }
}
