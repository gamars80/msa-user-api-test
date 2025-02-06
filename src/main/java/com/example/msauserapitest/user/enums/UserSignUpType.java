package com.example.msauserapitest.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserSignUpType {
    APP_USER("앱회원"),
    MANAGER("관리자"),
    NO_USER("비회원"),
    DUPLICATED_EXCEPT_PHONE_USER("휴대전화번호 외 정보 중복회원")
    //NONRESIDENT_USER("비거주회원"), //비거주회원은 김집사 서비스를 사용할 수 없음. = 비거주회원은 존재할 수 없음.
    //DEPENDENT_USER("종속회원"),  //종속회원은 가입(JOIN)과 무관함
    ;

    private final String description;
}
