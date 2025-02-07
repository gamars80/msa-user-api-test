package com.example.msauserapitest.user.service;


import com.example.msauserapitest.user.dto.UserAccountDto;

import java.util.Optional;

public interface AccountCommonService {
    Optional<UserAccountDto> findAccountByUserId(Long userId);
    Optional<UserAccountDto> findAccountByLoginId(String loginId);
}
