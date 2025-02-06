package com.example.msauserapitest.auth.dto;

import kr.co.gacha.user.RequestUser;
import kr.co.gacha.user.enums.AccountType;
import kr.co.gacha.user.enums.RoleType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static kr.co.gacha.user.enums.RoleType.*;

public class AuthenticatedUser implements UserDetails, RequestUser {

    private static final String ROLE_PREFIX = "ROLE_";

    private final transient AccountFindResponse account;

    public AuthenticatedUser(AccountFindResponse account) {
        this.account = account;
    }

    public Long getUserId() {
        return account.getUserId();
    }

    @Override
    public Long getUserAccountId() {
        return account.getAccountType().equals(AccountType.USER) ? account.getAccountId() : 0L;
    }

    @Override
    public Long getAdminAccountId() {
        return account.getAccountType().equals(AccountType.ADMIN) ? account.getAccountId() : 0L;
    }

    @Override
    public RoleType getRole() {
        return account.getRole();
    }

    @Override
    public AccountType getAccountType() {
        return account.getAccountType();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.account.hasSuperAdminRoles()) {
            return getGrantedAuthorityList(SUPER_ADMIN, ADMIN, CUSTOMER);
        }

        if (this.account.hasAdminRoles()) {
            return getGrantedAuthorityList(ADMIN, CUSTOMER);
        }

        return getGrantedAuthorityList(CUSTOMER);
    }

    private static List<GrantedAuthority> getGrantedAuthorityList(RoleType... roleTypes) {
        return Arrays.stream(roleTypes).map(AuthenticatedUser::getGrantedAuthority).toList();
    }

    private static GrantedAuthority getGrantedAuthority(RoleType roleType) {
        return () -> ROLE_PREFIX + roleType.name();
    }

    @Override
    public String getPassword() {
        return Long.toString(account.getAccountId());
    }

    @Override
    public String getUsername() {
        return Long.toString(account.getAccountId());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
