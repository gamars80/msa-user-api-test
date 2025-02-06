package com.example.msauserapitest.provider.dto;

public class AccessToken {
    private final String tokenValue;

    public AccessToken(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public String getValue() {
        return tokenValue;
    }
}
