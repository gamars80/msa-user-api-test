package com.example.msauserapitest.user.exception;

import jakarta.ws.rs.BadRequestException;

public class UnableSignUpException extends BadRequestException {

    private static final String ERROR_CODE = "UNABLE_SIGN_UP_";
    private static final String SERVER_MESSAGE = "회원가입 실패";

    public UnableSignUpException(final UnableSignUpReasonMessage reason) {
        super(String.format("%s -> %s", SERVER_MESSAGE, reason.getDescription()));
    }
}
