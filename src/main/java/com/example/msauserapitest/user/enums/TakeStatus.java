package com.example.msauserapitest.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TakeStatus {
    REQUEST("수령요청"),
    FINISH_TAKE("수령완료"),
    NOT_TAKE("미수령");
    
    private final String description;
}