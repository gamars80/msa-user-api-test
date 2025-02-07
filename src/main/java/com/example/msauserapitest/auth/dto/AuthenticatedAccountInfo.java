package com.example.msauserapitest.auth.dto;

import com.example.msauserapitest.user.dto.UserAccountDto;
import com.example.msauserapitest.user.enums.JoinType;
import lombok.Getter;

@Getter
public class AuthenticatedAccountInfo {
    private final Long userId;

    private JoinType joinType;
    private String loginId;
    private String name;
    private String phoneNumber;
    private String profileImageUrl;
    private String nickname;

    public AuthenticatedAccountInfo(UserAccountDto account) {
        this.userId = account.getUserId();
        this.loginId = account.getLoginId();
        this.name = account.getUser().getName();
        this.phoneNumber = account.getUser().getPhoneNumber();
        this.profileImageUrl = account.getProfileImageUrl();
        this.nickname = account.getNickname();
        this.joinType = account.getJoinType();
    }
}
