package com.example.msauserapitest.user.service.impl;

import com.example.msauserapitest.user.enums.RoleType;
import com.example.msauserapitest.user.service.dto.UserDto;
import jakarta.validation.constraints.NotNull;

public interface UserCommonService {
    UserDto findById(@NotNull Long id);

    RoleType findRoleById(@NotNull Long id);
}
