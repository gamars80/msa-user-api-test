package com.example.msauserapitest.user.repository;

import com.example.msauserapitest.user.domain.User;
import com.example.msauserapitest.user.enums.RoleType;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User getById(Long id);

    Optional<User> findById(Long userId);

    User save(User user);

    Boolean existsByPhoneNumber(String phoneNumber);

    Optional<User> findByPhoneNumber(String phoneNumber);

    List<User> findAllByIdIn(List<Long> ids);

    List<User> findByName(String createdBy);

    List<User> findByNameContaining(String namePart);

    List<User> findByRoleType(RoleType roleType);
}
