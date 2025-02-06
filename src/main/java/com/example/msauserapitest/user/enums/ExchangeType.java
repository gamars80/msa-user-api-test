package com.example.msauserapitest.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExchangeType {
    REQUEST("요청"),
    RESPONSE("응답");

    private final String description;
}