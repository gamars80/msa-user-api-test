package com.example.msauserapitest.user.exception;

import com.example.msauserapitest.auth.exception.BadRequestException;

public class UserExchangeException extends BadRequestException {

    private static final String ERROR_CODE = "EXCHANGE_ERROR";

    public UserExchangeException(final String message, String id) {
        super(message + " -> value: " + id, message, ERROR_CODE);
    }
}