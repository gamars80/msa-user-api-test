package com.example.msauserapitest.user.repository;

import com.example.msauserapitest.user.domain.User;
import com.example.msauserapitest.user.domain.UserAccount;

import java.util.Optional;

public interface UserAccountRepository {

    UserAccount save(UserAccount userAccount);

    Boolean existsByUser(User user);

    Boolean existsByLoginId(String loginId);

    Optional<UserAccount> findById(Long id);

    Optional<UserAccount> findByUser(User user);

    UserAccount findByUserId(Long userId);

    Optional<UserAccount> findByLoginId(String loginId);
}
