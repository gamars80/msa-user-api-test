package com.example.msauserapitest.user.service;

import com.example.msauserapitest.auth.dto.AccountFindResponse;
import jakarta.validation.constraints.NotNull;

public interface AccountFindService {
    AccountFindResponse findAccountByUserId(@NotNull Long userId);
}
