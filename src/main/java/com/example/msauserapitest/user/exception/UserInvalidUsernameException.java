package com.example.msauserapitest.user.exception;

import jakarta.ws.rs.BadRequestException;

public class UserInvalidUsernameException extends BadRequestException {

    private static final String ERROR_CODE = "USER_INVALID_USERNAME";
    private static final String SERVER_MESSAGE = "유효하지 않은 사용자 이름";
    private static final String CLIENT_MESSAGE = "유효하지 않은 사용자 이름입니다.";

    public UserInvalidUsernameException(final String username) {
        super(String.format("%s -> username: %s", SERVER_MESSAGE, username));
    }
}
