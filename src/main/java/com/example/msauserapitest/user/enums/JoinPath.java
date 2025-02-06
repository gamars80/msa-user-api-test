package com.example.msauserapitest.user.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum JoinPath {
    MOBILE("모바일"),
    PC("PC");

    private final String description;
}