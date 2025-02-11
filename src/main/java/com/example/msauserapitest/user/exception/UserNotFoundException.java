package com.example.msauserapitest.user.exception;


import jakarta.ws.rs.NotFoundException;

import java.time.LocalDate;

public class UserNotFoundException extends NotFoundException {

    private static final String ERROR_CODE = "USER_NOT_FOUND";
    private static final String SERVER_MESSAGE = "존재하지 않는 유저 조회";
    private static final String CLIENT_MESSAGE = "사용자를 찾지 못했습니다.";

    public UserNotFoundException(final Long id) {
        super(String.format("%s -> user id: %d", SERVER_MESSAGE, id));
    }

    public UserNotFoundException(String phoneNumber) {
        super(String.format("%s -> phoneNumber : %s", SERVER_MESSAGE, phoneNumber));
    }

    public UserNotFoundException(String name, LocalDate birth, String phoneNumber) {
        super(String.format("%s -> name : %s, birth : %s, phoneNumber : %s", SERVER_MESSAGE, name, birth, phoneNumber));
    }
}
