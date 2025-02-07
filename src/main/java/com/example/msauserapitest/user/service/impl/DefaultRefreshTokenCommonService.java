package com.example.msauserapitest.user.service.impl;

import com.example.msauserapitest.user.domain.RefreshToken;
import com.example.msauserapitest.user.domain.User;
import com.example.msauserapitest.user.repository.RefreshTokenRepository;
import com.example.msauserapitest.user.repository.UserRepository;
import com.example.msauserapitest.user.service.RefreshTokenCommonService;
import com.example.msauserapitest.user.service.dto.RefreshTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DefaultRefreshTokenCommonService implements RefreshTokenCommonService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserEntityService userEntityService;

    @Override
    public RefreshTokenDto create(Long userId) {
        User user = userRepository.getById(userId);
        String tokenValue = createTokenValue();
        RefreshToken savedRefreshToken = refreshTokenRepository.save(RefreshToken.create(tokenValue, user));

        return new RefreshTokenDto(savedRefreshToken);
    }

    private String createTokenValue() {
        String tokenValue = RefreshToken.makeValue();
        while (refreshTokenRepository.existsByValue(tokenValue)) {
            tokenValue = RefreshToken.makeValue();
        }

        return tokenValue;
    }

    @Override
    public RefreshTokenDto findByValue(String refreshTokenValue) {
        RefreshToken refreshToken = refreshTokenRepository.findById(refreshTokenValue)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 리프레시 토큰입니다. refreshToken=" + refreshTokenValue));

        return new RefreshTokenDto(refreshToken);
    }

    @Override
    public void delete(Long userId) {
        User user = userRepository.getById(userId);
        refreshTokenRepository.deleteByUser(user);
    }
}
