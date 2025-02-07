package com.example.msauserapitest.auth.dto;


import com.example.msauserapitest.user.dto.UserAccountDto;
import com.example.msauserapitest.user.enums.AccountType;
import com.example.msauserapitest.user.enums.RoleType;
import lombok.Getter;

@Getter
public class AccountFindResponse {

    private final AccountType accountType;
    private final Long accountId;
    private String loginId;
    private final User user;

    public AccountFindResponse(UserAccountDto account, RoleType roleTypeOfUser) {
        accountType = AccountType.USER;
        accountId = account.getId();
        user = new User(account.getUser().getId(), roleTypeOfUser);
    }


    public AccountFindResponse () {
        accountType = AccountType.USER;
        accountId = 0L;
        loginId = "";
        user =  new User(0L, RoleType.CUSTOMER);
    }

    public Long getUserId() {
        return user.getId();
    }

    public RoleType getRole() {
        return user.getRoleType();
    }

    public String getRoleName() {
        return user.getRoleType().name();
    }

    public Long getUserAccountId() {
        return accountType.equals(AccountType.USER) ? accountId : 0L;
    }

    public Long getAdminAccountId() {
        return accountType.equals(AccountType.ADMIN) ? accountId : 0L;
    }

    public boolean hasSuperAdminRoles() {
        return this.getRoleName().equals(RoleType.SUPER_ADMIN.name());
    }

    public boolean hasAdminRoles() {
        return this.getRoleName().equals(RoleType.ADMIN.name());
    }


    @Getter
    private static class User {

        private final Long id;

        private final RoleType roleType;

        User(Long id, RoleType roleType) {
            this.id = id;
            this.roleType = roleType;
        }
    }
}
