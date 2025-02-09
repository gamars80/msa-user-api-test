package com.example.msauserapitest.user.repository;

import com.example.msauserapitest.user.domain.User;
import com.example.msauserapitest.user.domain.UserAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountJpaRepository extends JpaRepository<UserAccount, Long> {

    Optional<UserAccount> findByUser(User user);

    Boolean existsByUser(User User);

    Boolean existsByLoginId(String loginId);

    Optional<UserAccount> findByUser_Id(Long userId);

    Optional<UserAccount> findByLoginId(String loginId);

    @Query(value = "SELECT count(ua.account_id) FROM user_account ua WHERE ua.account_status = 'OUT' and " +
            "ua.deleted = true and ua.login_id = :loginId ", nativeQuery = true)
    Integer getSignOutAccountCount(@Param("loginId") String loginId);

    @Query(value = "SELECT count(ua.account_id) FROM user_account ua WHERE ua.account_status = 'OUT' and ua.is_black = true and " +
            "ua.deleted = true and ua.login_id = :loginId ", nativeQuery = true)
    Integer getBlackHistoryAccountCount(@Param("loginId") String loginId);
}
