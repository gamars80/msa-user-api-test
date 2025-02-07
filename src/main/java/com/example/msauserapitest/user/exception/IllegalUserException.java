package com.example.msauserapitest.user.exception;

import com.example.msauserapitest.auth.exception.BadRequestException;

public class IllegalUserException extends BadRequestException {
    private static final String ERROR_CODE = "ILLEGAL_USER";
    private static final String SERVER_MESSAGE = "유효하지 않은 사용자";
    private static final String CLIENT_MESSAGE = "존재할 수 없는 사용자입니다. 관리자 확인이 필요합니다.";

    public IllegalUserException(final Long userId) {
        super(String.format("%s -> userId: %d", SERVER_MESSAGE, userId), CLIENT_MESSAGE, ERROR_CODE);
    }
}
