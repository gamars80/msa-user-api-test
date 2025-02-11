package com.example.msauserapitest.user.repository;

import com.example.msauserapitest.user.domain.User;
import com.example.msauserapitest.user.enums.RoleType;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository userRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Boolean existsByPhoneNumber(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public List<User> findAllByIdIn(List<Long> ids) {
        return userRepository.findAllByIdIn(ids);
    }


    @Override
    public List<User> findByName(String createdBy) {
        return userRepository.findByName(createdBy);
    }

    @Override
    public List<User> findByNameContaining(String namePart) {
        return userRepository.findByNameContaining(namePart);
    }

    @Override
    public User getById(Long userId) {
        return findById(userId).orElseThrow(() -> new IllegalArgumentException("정보를 찾을수 없습니다"));
    }

    @Override
    public List<User> findByRoleType(RoleType roleType) {
        return userRepository.findByRoleType(roleType);
    }
}
