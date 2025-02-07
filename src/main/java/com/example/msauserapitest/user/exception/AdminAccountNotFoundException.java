package com.example.msauserapitest.user.exception;

import com.example.msauserapitest.auth.exception.BadRequestException;

public class AdminAccountNotFoundException extends BadRequestException {

    private static final String ERROR_CODE = "ADMIN_ACCOUNT_NOT_FOUND";
    private static final String ADMIN_ACCOUNT_NOT_FOUND = "비밀번호가 일치하지 않습니다.";

    public AdminAccountNotFoundException(final String loginId, String password) {
        super(ADMIN_ACCOUNT_NOT_FOUND + " -> loginId: " + loginId + ", password: " + password, ADMIN_ACCOUNT_NOT_FOUND, ERROR_CODE);
    }

    public AdminAccountNotFoundException(Long adminAccountId) {
        super(ADMIN_ACCOUNT_NOT_FOUND + " -> adminAccountId: " + adminAccountId, ADMIN_ACCOUNT_NOT_FOUND, ERROR_CODE);
    }
}
