package com.example.msauserapitest.user.exception;

import jakarta.ws.rs.BadRequestException;

public class InvalidBirthChangeException extends BadRequestException {

    private static final String ERROR_CODE = "INVALID_BIRTH_CHANGE";
    private static final String SERVER_MESSAGE = "유효하지 않은 생일 변경";
    private static final String CLIENT_MESSAGE = "14세 미만의 아동으로 신청을 원하실 경우 [신청취소>회원가입]으로 신청해주세요.";

    public InvalidBirthChangeException(final Long userId) {
        super(String.format("%s -> userId: %d", SERVER_MESSAGE, userId));
    }
}
