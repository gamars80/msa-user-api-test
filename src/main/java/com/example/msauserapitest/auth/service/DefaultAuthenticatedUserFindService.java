package com.example.msauserapitest.auth.service;

import kr.co.gacha.user.RequestUser;
import kr.co.gacha.user.service.AuthenticatedUserFindService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class DefaultAuthenticatedUserFindService implements AuthenticatedUserFindService {

    public RequestUser findRequestUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof RequestUser)) {
            throw new IllegalArgumentException("현재 로그인 유저를 찾을 수 없습니다.");
        }

        return (RequestUser) authentication.getPrincipal();
    }
}
