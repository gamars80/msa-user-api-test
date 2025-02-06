package com.example.msauserapitest.auth.dto;

import kr.co.gacha.auth.provider.dto.AccessToken;
import kr.co.gacha.user.enums.JoinType;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenIssueResponse {
    private String accessToken;
    private Long userId;
    private String message;
    private JoinType joinType;

    private String loginId;
    private String name;
    private String phoneNumber;
    private String profileImageUrl;
    private String nickname;

    public TokenIssueResponse(AccessToken accessToken, Long userId, String message) {
        this.accessToken = accessToken.getValue();
        this.userId = userId;
        this.message = message;
    }
}
