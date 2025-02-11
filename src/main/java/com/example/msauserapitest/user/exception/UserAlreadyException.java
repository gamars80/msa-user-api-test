package com.example.msauserapitest.user.exception;

import jakarta.ws.rs.BadRequestException;

public class UserAlreadyException extends BadRequestException {

    private static final String ERROR_CODE = "USER_ALREADY";
    private static final String ALREADY_USER_ACCOUNT = "이미 존재하는 회원입니다.";
    private static final String ALREADY_USER_PHONE_NUMBER = "이미 사용중인 폰번호입니다.";

    public UserAlreadyException(final Long userId) {
        super(String.format("%s -> userId : %s", ALREADY_USER_ACCOUNT, userId));
    }

    public UserAlreadyException(final String phoneNumber) {
        super(String.format("%s -> phoneNumber : %s", ALREADY_USER_PHONE_NUMBER, phoneNumber));
    }
}
