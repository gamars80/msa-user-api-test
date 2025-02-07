package com.example.msauserapitest.user.service.impl;

import com.example.msauserapitest.auth.dto.AccountFindResponse;
import com.example.msauserapitest.user.dto.UserAccountDto;
import com.example.msauserapitest.user.service.AccountCommonService;
import com.example.msauserapitest.user.service.AccountFindService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DefaultAccountFindService implements AccountFindService {
    private final AccountCommonService accountCommonService;
    private final UserCommonService userCommonService;

    public AccountFindResponse findAccountByUserId(@NotNull Long userId) {
        if(userId != 0L) {
            Optional<UserAccountDto> foundAccount = accountCommonService.findAccountByUserId(userId);
            if (foundAccount.isPresent()) {
                return new AccountFindResponse(foundAccount.get(), userCommonService.findRoleById(userId));
            }

            throw new IllegalArgumentException("사용자 계정을 찾을 수 없습니다. userId=" + userId);
        }

        return new AccountFindResponse();


    }

//    public AccountFindResponse findAdminAccountByUserId(@NotNull Long userId) {
//
//        Optional<AdminAccountDto> foundAdminAccount = accountCommonService.findAdminAccountByUserId(userId);
//        if (foundAdminAccount.isPresent()) {
//            return new AccountFindResponse(foundAdminAccount.get(), userCommonService.findRoleById(userId));
//        }
//
//        throw new IllegalArgumentException("관리자 계정을 찾을 수 없습니다. userId=" + userId);
//    }
}
