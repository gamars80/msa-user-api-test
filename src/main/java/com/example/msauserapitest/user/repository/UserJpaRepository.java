package com.example.msauserapitest.user.repository;

import com.example.msauserapitest.user.domain.User;
import com.example.msauserapitest.user.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {

    Optional<User> findByPhoneNumber(String phoneNumber);

    Boolean existsByPhoneNumber(String phoneNumber);

    List<User> findAllByIdIn(List<Long> ids);

    List<User> findByName(String createdBy);

    List<User> findByNameContaining(String namePart);

    List<User> findByRoleType(RoleType roleType);
}
