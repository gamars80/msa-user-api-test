package com.example.msauserapitest.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CheckPhoneNumberStatus {
    APP_USER("앱회원"),
    MANAGER("관리자"),
    APP_AND_MANAGER("앱회원 + 관리자"),
    NO_APP_USER("비회원 또는 가족회원"),

    NO_CHECK("휴대폰 번호로 회원 여부 체크하지 않음");

    private final String description;
}
