package com.example.msauserapitest.user.exception;

import com.example.msauserapitest.auth.exception.NotFoundException;

public class UserTokenNotFoundException extends NotFoundException {

    private static final String ERROR_CODE = "USER_TOKEN_NOT_FOUND";
    private static final String SERVER_MESSAGE = "유효하지 않은 토큰을 가진 유저 조회";
    private static final String CLIENT_MESSAGE = "사용자의 토큰을 찾지 못했습니다.";

    public UserTokenNotFoundException(final long id, final String token) {
        super(String.format("%s -> user id: %d, token: %s", SERVER_MESSAGE, id, token), CLIENT_MESSAGE, ERROR_CODE);
    }
}
