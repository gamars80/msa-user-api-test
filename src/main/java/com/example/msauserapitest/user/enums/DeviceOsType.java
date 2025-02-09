package com.example.msauserapitest.user.enums;

import com.example.msauserapitest.utils.DescriptionEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DeviceOsType implements DescriptionEnum {
    AOS("안드로이드"),
    IOS("아이오에스");

    private final String description;
}
