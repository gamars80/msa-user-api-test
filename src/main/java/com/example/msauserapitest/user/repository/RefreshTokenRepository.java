package com.example.msauserapitest.user.repository;

import com.example.msauserapitest.user.domain.RefreshToken;
import com.example.msauserapitest.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    boolean existsByValue(String value);

    void deleteByUser(User user);
}
