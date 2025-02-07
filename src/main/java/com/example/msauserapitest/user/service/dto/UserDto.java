package com.example.msauserapitest.user.service.dto;

import com.example.msauserapitest.user.domain.User;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;


@Getter
public final class UserDto {
    private final Long id;
    private final String name;
    private final String phoneNumber;

    public UserDto(@NotNull final User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.phoneNumber = user.getPhoneNumber();
    }
}
