package com.example.msauserapitest.user.service.impl;

import com.example.msauserapitest.user.domain.User;
import com.example.msauserapitest.user.enums.RoleType;
import com.example.msauserapitest.user.repository.UserRepository;
import com.example.msauserapitest.user.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class DefaultUserCommonService implements UserCommonService {
    private final UserRepository userRepository;

    public UserDto findById(Long id) {
        User user = userRepository.getById(id);
        return new UserDto(user);
    }

    public RoleType findRoleById(Long id) {
        return userRepository.getById(id).getRoleType();
    }
}
