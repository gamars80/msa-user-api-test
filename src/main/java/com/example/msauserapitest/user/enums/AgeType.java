package com.example.msauserapitest.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AgeType {
    CHILD("14세 미만 아동"),
    MINOR("미성년자"),
    ADULT("성인");

    private final String description;
}
