//package com.example.msauserapitest.user.exception;
//
//public class UnauthorizedManagerException extends UnauthorizedException {
//    private static final String ERROR_CODE = "UNAUTHORIZED_MANAGER";
//    private static final String SERVER_MESSAGE = "권한이 없는 관리자";
//    private static final String CLIENT_MESSAGE = "권한이 없습니다.";
//
//    public UnauthorizedManagerException(Long userId, String loginId, Long apartmentComplexId) {
//        super(
//                String.format("%s -> userId: %d, loginId: %s, apartmentComplex id: %d", SERVER_MESSAGE, userId, loginId, apartmentComplexId),
//                CLIENT_MESSAGE,
//                ERROR_CODE
//        );
//    }
//}
