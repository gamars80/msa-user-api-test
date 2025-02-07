package com.example.msauserapitest.user.exception;

import com.example.msauserapitest.auth.exception.BadRequestException;

public class UserApproveUpdateException extends BadRequestException {

    private static final String ERROR_CODE = "USER_APPROVE_UPDATE";
    private static final String NOT_FOUND_REJECT_REASON = "반려 사유를 입력해주세요.";

    public UserApproveUpdateException() {
        super(String.format(NOT_FOUND_REJECT_REASON), NOT_FOUND_REJECT_REASON, ERROR_CODE);
    }

}
