package com.example.msauserapitest.auth.provider;

import com.example.msauserapitest.auth.provider.dto.AccessToken;
import com.example.msauserapitest.auth.provider.dto.AccessTokenContent;
import com.example.msauserapitest.auth.provider.dto.AccessTokenValidationResult;
import com.example.msauserapitest.user.enums.RoleType;

import java.util.Optional;

public interface AccessTokenProvider {
    AccessToken create(Long userId, RoleType roleType);

    AccessToken create(Long userId, Boolean isManager, RoleType roleType);

    AccessTokenValidationResult validate(AccessToken token);

    AccessTokenContent getContent(AccessToken token);

    Optional<Object> getClaimValue(AccessToken token, String claimName);
}
