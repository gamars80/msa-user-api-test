package com.example.msauserapitest.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UnableSignUpReasonMessage {
    PERSONAL_INFORMATION_AGREE_IS_REQUIRED("개인정보 제공 동의는 필수입니다."),
    CHILDREN_CAN_SIGN_UP_AFTER_ADDING_FAMILY("14세 미만 아동은 가족 추가 후 가입 가능합니다."),
    PHONE_NUMBER_ALREADY_EXISTS("이미 존재하는 휴대폰 번호입니다."),
    USER_ALREADY_EXISTS("이미 존재하는 회원입니다."),
    MANAGER_NOT_MATCHING("관리자 계정정보와 휴대전화번호가 일치하지 않습니다. 관리자에게 문의하세요."),
    USER_DESTROYED("잘못된 회원정보 입니다. 관리자에게 문의하세요.");

    private final String description;
}
