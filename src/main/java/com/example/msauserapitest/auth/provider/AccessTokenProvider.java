package com.example.msauserapitest.auth.provider;

import com.example.msauserapitest.auth.provider.dto.AccessToken;
import com.example.msauserapitest.auth.provider.dto.AccessTokenContent;
import com.example.msauserapitest.auth.provider.dto.AccessTokenValidationResult;

import java.util.Optional;

public interface AccessTokenProvider {
    AccessToken create(Long userId);

    AccessToken create(Long userId, Boolean isManager);

    AccessTokenValidationResult validate(AccessToken token);

    AccessTokenContent getContent(AccessToken token);

    Optional<Object> getClaimValue(AccessToken token, String claimName);
}
