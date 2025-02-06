package com.example.msauserapitest.user.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum AccountStatus {
    OUT("탈퇴"),
    FREEZE("휴면"),
    NORMAL("정상");

    private final String description;
}
