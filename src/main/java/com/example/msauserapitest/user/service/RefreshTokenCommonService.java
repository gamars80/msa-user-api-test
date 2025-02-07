package com.example.msauserapitest.user.service;

import com.example.msauserapitest.user.service.dto.RefreshTokenDto;

public interface RefreshTokenCommonService {
    RefreshTokenDto create(Long userId);

    RefreshTokenDto findByValue(String refreshTokenValue);

    void delete(Long userId);
}
