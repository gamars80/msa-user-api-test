package com.example.msauserapitest.user.enums;

import lombok.Getter;

@Getter
public enum ChangedApprovalStatusNotificationTitleAndMessage {
    APPROVE("김집사PASS 승인안내", "[김집사PASS 승인안내]\n김집사PASS 승인이 완료되었어요."),
    REJECT("김집사PASS 승인취소 안내", "[김집사PASS 승인취소 안내]\n김집사PASS 승인이 취소됐어요. 관리사무소에 문의해 주세요.");

    private final String title;
    private final String message;

    private ChangedApprovalStatusNotificationTitleAndMessage(String title, String message) {
        this.title = title;
        this.message = message;
    }
}
