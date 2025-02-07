package com.example.msauserapitest.auth.service;

import com.example.msauserapitest.auth.dto.AuthenticatedAccountInfo;
import com.example.msauserapitest.auth.dto.LoginRequest;
import com.example.msauserapitest.auth.dto.TokenIssueResponse;
import com.example.msauserapitest.auth.provider.AccessTokenProvider;
import com.example.msauserapitest.user.dto.UserAccountDto;
import com.example.msauserapitest.user.service.AccountCommonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class LoginService {
    private final AccessTokenProvider accessTokenProvider;
    private final AccountCommonService accountCommonService;

    public TokenIssueResponse login(LoginRequest request) {
        AuthenticatedAccountInfo user = this.authenticateAppUser(request.getLoginId());

        if (user != null) {
            return TokenIssueResponse.builder()
                    .accessToken(accessTokenProvider.create(user.getUserId()).getValue())
                    .userId(user.getUserId())
                    .loginId(user.getLoginId())
                    .name(user.getName())
                    .phoneNumber(user.getPhoneNumber())
                    .joinType(user.getJoinType())
                    .profileImageUrl(user.getProfileImageUrl())
                    .nickname(user.getNickname())
                    .build();
        } else {
            return TokenIssueResponse.builder()
                    .message("존재 하지 않는 계정 정보 입니다.")
                    .build();
        }
    }

    private AuthenticatedAccountInfo authenticateAppUser(String loginId) {
        Optional<UserAccountDto> userAccountDto = accountCommonService.findAccountByLoginId(loginId);

        if (userAccountDto.isPresent()) {
            return new AuthenticatedAccountInfo(userAccountDto.get());
        }

        return null;
    }

    public TokenIssueResponse notUserToken() {
        return TokenIssueResponse.builder()
                .accessToken(accessTokenProvider.create(0L).getValue())
                .build();
    }
}
