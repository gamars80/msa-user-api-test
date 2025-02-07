package com.example.msauserapitest.user.exception;

import com.example.msauserapitest.auth.exception.NotFoundException;

public class AccountNotFoundException extends NotFoundException {

    private static final String ERROR_CODE = "Account_NOT_FOUND";
    private static final String SERVER_MESSAGE = "존재하지 않는 계정 조회";
    private static final String CLIENT_MESSAGE = "계정을 찾지 못했습니다.";

    public AccountNotFoundException(final Long id) {
        super(String.format("%s -> user id: %d", SERVER_MESSAGE, id), CLIENT_MESSAGE, ERROR_CODE);
    }
}