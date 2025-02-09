package com.example.msauserapitest.user.repository;

import com.example.msauserapitest.user.domain.User;
import com.example.msauserapitest.user.domain.UserAccount;
import com.example.msauserapitest.user.exception.AccountNotFoundException;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserAccountRepositoryAdapter implements UserAccountRepository {
    private final AccountJpaRepository accountRepository;
    private final JPAQueryFactory queryFactory;

    @Override
    public UserAccount save(UserAccount userAccount) {
        return accountRepository.save(userAccount);
    }

    @Override
    public Optional<UserAccount> findById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Optional<UserAccount> findByUser(User user) {
        return accountRepository.findByUser(user);
    }

    @Override
    public Boolean existsByUser(User user) {
        return accountRepository.existsByUser(user);
    }

    @Override
    public Boolean existsByLoginId(String loginId) {
        return accountRepository.existsByLoginId(loginId);
    }

    @Override
    public Optional<UserAccount> findByLoginId(String loginId) {
        return accountRepository.findByLoginId(loginId);
    }


    @Override
    public UserAccount findByUserId(Long userId) {
        return accountRepository.findByUser_Id(userId)
                .orElseThrow(() -> new AccountNotFoundException(userId));
    }
}