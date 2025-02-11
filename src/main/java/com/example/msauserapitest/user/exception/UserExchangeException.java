package com.example.msauserapitest.user.exception;

import jakarta.ws.rs.BadRequestException;

public class UserExchangeException extends BadRequestException {

    private static final String ERROR_CODE = "EXCHANGE_ERROR";

    public UserExchangeException(final String message, String id) {
        super(message + " -> value: " + id);
    }
}