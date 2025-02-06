package com.example.msauserapitest.auth.dto;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class LoginConditionsCheckResponse {
    private Boolean isUser;
    private Boolean isDormantAccount;
    private Boolean isSameDeviceKey;

    public static LoginConditionsCheckResponse create() {
        return LoginConditionsCheckResponse.builder()
                .isUser(true)
                .isDormantAccount(false)
                .isSameDeviceKey(false)
                .build();
    }

    public LoginConditionsCheckResponse updateIsUser(boolean isUser) {
        this.isUser = isUser;

        return this;
    }

    public LoginConditionsCheckResponse updateIsDormantAccount(boolean isDormantAccount) {
        this.isDormantAccount = isDormantAccount;

        return this;
    }

    public LoginConditionsCheckResponse updateSameDeviceKey(boolean isSameDeviceKey) {
        this.isSameDeviceKey = isSameDeviceKey;

        return this;
    }
}
