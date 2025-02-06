package com.example.msauserapitest.user;

import com.example.msauserapitest.user.enums.AccountType;
import com.example.msauserapitest.user.enums.RoleType;

public interface RequestUser {

    Long getUserId();

    Long getUserAccountId();

    Long getAdminAccountId();

    RoleType getRole();

    AccountType getAccountType();
}
