package com.example.msauserapitest.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExchangeStatus {
    REQUEST("요청중"),
    WAIT_CONFIRM("응답수락 대기"),
    CONFIRM("교환완료");
    

    private final String description;
}