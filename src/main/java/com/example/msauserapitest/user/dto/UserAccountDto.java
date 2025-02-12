package com.example.msauserapitest.user.dto;

import com.example.msauserapitest.user.domain.User;
import com.example.msauserapitest.user.domain.UserAccount;
import com.example.msauserapitest.user.enums.JoinType;
import com.example.msauserapitest.user.enums.RoleType;
import com.example.msauserapitest.user.service.dto.UserDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserAccountDto {
    private Long id;
    private Long accountId;
    private String loginId;
    private String profileImageUrl;
    private String nickname;
    private JoinType joinType;
    private UserDto user;
    private Boolean isTester;
    private RoleType roleType;


    public static UserAccountDto from(UserAccount account) {
        UserAccountDto accountDto = new UserAccountDto();
        accountDto.joinType = account.getJoinType();
        accountDto.id = account.getId();
        accountDto.loginId = account.getLoginId();
        accountDto.user = new UserDto(account.getUser());
        accountDto.profileImageUrl = account.getProfileImageUrl();
        accountDto.roleType = account.getUser().getRoleType();
        return accountDto;
    }

    public static UserAccountDto of(Long id, User user) {
        UserAccountDto accountDto = new UserAccountDto();
        accountDto.id = id;
        accountDto.user = new UserDto(user);

        return accountDto;
    }

    public Long getUserId() {
        return user.getId();
    }
}
