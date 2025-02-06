package com.example.msauserapitest.auth.service;

//
//@Transactional(readOnly = true)
//@RequiredArgsConstructor
//@Service
//public class AdminLoginService {
//    private final AccessTokenProvider accessTokenProvider;
//    private final RefreshTokenCommonService refreshTokenService;
//    private final AdminAccountService adminAccountService;
//    private final PasswordEncoder bcryptEncoder;
//
//    public AdminTokenIssueResponse login(AdminLoginRequest request) {
//
//        Optional<AdminAccount> account = adminAccountService.findAdminAccountByLoginId(request.getLoginId());
//
//        if (account.isEmpty()) {
//            throw new AdminAccountNotFoundException(request.getLoginId(), request.getPassword());
//        }
//
//        if (!bcryptEncoder.matches(request.getPassword(), account.get().getPassword())) {
//            throw new AdminAccountNotFoundException(request.getLoginId(), request.getPassword());
//        }
//
//        return AdminTokenIssueResponse.builder()
//                .accessToken(accessTokenProvider.create(account.get().getUserId(), true))
//                .refreshToken(refreshTokenService.create(account.get().getUserId()))
//                .userId(account.get().getUserId())
//                .build();
//    }
//}
