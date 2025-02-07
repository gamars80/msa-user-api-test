package com.example.msauserapitest.user.service.impl;

import com.example.msauserapitest.user.domain.User;
import com.example.msauserapitest.user.domain.UserAccount;
import com.example.msauserapitest.user.dto.UserAccountDto;
import com.example.msauserapitest.user.repository.UserAccountRepository;
import com.example.msauserapitest.user.repository.UserRepository;
import com.example.msauserapitest.user.service.AccountCommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class DefaultAccountCommonService implements AccountCommonService {
    private final UserRepository userRepository;
    private final UserAccountRepository userAccountRepository;
    private final UserEntityService userEntityService;

    public Optional<UserAccountDto> findAccountByUserId(Long userId) {
        if (userId != 0L) {
            User user = userRepository.getById(userId);

            Optional<UserAccount> optionalAccount = userAccountRepository.findByUser(user);
            if (optionalAccount.isEmpty()) {
                return Optional.empty();
            }

            UserAccount account = optionalAccount.get();
            return Optional.of(UserAccountDto.from(account));
        }

        return Optional.empty();
    }

    public Optional<UserAccountDto> findAccountByLoginId(String loginId) {

        Optional<UserAccount> account = userAccountRepository.findByLoginId(loginId);

        if (account.isPresent()) {
            return Optional.of(UserAccountDto.from(account.get()));
        }

        return Optional.empty();
    }

}
