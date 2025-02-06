package com.example.msauserapitest.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AppUserAndPhoneNumberMatchType {
    NO_APP_USER("비앱회원"),
    PHONE_NUMBER_OCCUPIED("이미 사용 중인 휴대전화번호"),
    PHONE_NUMBER_CHANGED("휴대전화번호 변경됨"),
    MATCHED("앱회원과 휴대전화번호 매치됨");

    private final String description;
}
