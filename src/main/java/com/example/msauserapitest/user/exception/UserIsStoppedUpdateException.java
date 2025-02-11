package com.example.msauserapitest.user.exception;

import jakarta.ws.rs.BadRequestException;

public class UserIsStoppedUpdateException extends BadRequestException {

    private static final String ERROR_CODE = "USER_IS_STOP_UPDATE";
    private static final String NOT_FOUND_STOP_REASON = "이용해지 사유를 입력해주세요.";
    private static final String NOT_FOUND_USER = "유저 정보가 존재하지 않습니다.";

    public UserIsStoppedUpdateException() {
        super(String.format(NOT_FOUND_STOP_REASON));
    }

    public UserIsStoppedUpdateException(final Long userId) {
        super(String.format("%s -> userId: %s", NOT_FOUND_USER, userId));
    }
}
