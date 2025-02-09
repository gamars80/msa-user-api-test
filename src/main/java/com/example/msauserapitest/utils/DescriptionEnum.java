package com.example.msauserapitest.utils;

public interface DescriptionEnum {
    String getDescription();

    default String getEnumDescriptionName() {
        return "";
    }
}
