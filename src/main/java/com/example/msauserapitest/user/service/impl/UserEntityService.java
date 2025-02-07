package com.example.msauserapitest.user.service.impl;

import com.example.msauserapitest.user.domain.UserAccount;
import com.example.msauserapitest.user.exception.UserNotFoundException;
import com.example.msauserapitest.user.repository.UserAccountRepository;
import com.example.msauserapitest.user.repository.UserRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Transactional
@Service
public class UserEntityService {
    private final UserRepository userRepository;
    private final UserAccountRepository userAccountRepository;

    public UserAccount findByLoginId(@NotNull String loginId) {
        return userAccountRepository.findByLoginId(loginId)
                .orElseThrow(() -> new UserNotFoundException(loginId));
    }

    public UserAccount findByAccountId(@NotNull Long id) {
        return userAccountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("등록된 앱 유저 정보를 찾을 수 없습니다. accountId=" + id));
    }
}
