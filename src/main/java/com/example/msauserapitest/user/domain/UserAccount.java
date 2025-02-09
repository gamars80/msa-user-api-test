package com.example.msauserapitest.user.domain;

import com.example.msauserapitest.user.enums.AccountStatus;
import com.example.msauserapitest.user.enums.JoinType;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

import static com.example.msauserapitest.utils.validation.UserValidationFailureMessages.Account.USER_CANNOT_BE_NULL;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;
@Entity
@Table(
        name = "user_account",
        indexes = {@Index(columnList = "user_id", name = "IDX_USER_ACCOUNT_USER_ID")}
)
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
@Getter
@Where(clause = "deleted=0")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="account_id", columnDefinition = "bigint")
    private final Long id = 0L;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    protected LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "last_modified_at")
    protected LocalDateTime lastModifiedAt;

    @CreatedBy
    @Column(name = "created_by", length = 30, nullable = false)
    protected Long createdBy = 0L;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    private Long lastModifiedBy = 0L;

    @Column(name = "deleted", nullable = false, columnDefinition = "bit default 0")
    private Boolean deleted = false;

    @Comment("유저")
    @NotNull(message = USER_CANNOT_BE_NULL)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Comment("로그인 아이디")
    @Column(name = "login_id", length = 20, nullable = false)
    private String loginId;

    @Builder.Default
    @Comment("프로필 이미지 URL")
    @Column(name = "profile_image_url", length = 255, nullable = false)
    private String profileImageUrl = "";

    @Comment("가입 타입")
    @Column(name = "join_type", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private JoinType joinType;

    @Builder.Default
    @Comment("상태")
    @Column(name = "account_status", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus = AccountStatus.NORMAL;

    @Builder.Default
    @Comment("개인정보 동의 여부")
    @Column(name = "is_personal_agree", nullable = false, columnDefinition = "BIT default 0")
    protected Boolean isPersonalAgree = false;

    @Builder.Default
    @Comment("마케팅 동의 여부")
    @Column(name = "is_marketing_agree")
    protected Boolean isMarketingAgree = false;

    @Builder.Default
    @Comment("푸시알람 여부")
    @Column(name = "is_use_push", nullable = false, columnDefinition = "BIT default 0")
    protected Boolean isUsePush = false;

    @Builder.Default
    @Comment("방생권 개수")
    @Column(name = "let_go_count", nullable = false)
    protected short letGoCount = (short) 0;


    @Column(name = "last_connect_at")
    protected LocalDateTime lastConnectAt;

    public static UserAccount create(
            @NotNull User user,
            String loginId,
            JoinType joinType,
            String profileImageUrl,
            boolean isPersonalAgree,
            boolean isMarketingAgree,
            short letGoCount
    ) {
        return UserAccount.builder()
                .user(user)
                .loginId(loginId)
                .joinType(joinType)
                .profileImageUrl(profileImageUrl)
                .isPersonalAgree(isPersonalAgree)
                .isMarketingAgree(isMarketingAgree)
                .letGoCount(letGoCount)
                .build();
    }

    public void plusLetGoCount(short letGoCount) {
        this.letGoCount += letGoCount;
    }

    public Long getUserId() {
        return user.getId();
    }

    public void updateLastConnect() {
        this.lastConnectAt = LocalDateTime.now();
    }

    public void updateProfileImage(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public void updateIsUsePush(Boolean isUsePush) {
        this.isUsePush = isUsePush;
    }

    public void updateIsMarketingAgree(Boolean isMarketingAgree) {
        this.isMarketingAgree = isMarketingAgree;
    }
}
