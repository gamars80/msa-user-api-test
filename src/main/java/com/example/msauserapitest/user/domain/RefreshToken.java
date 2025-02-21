package com.example.msauserapitest.user.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.Random;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(
        name = "refresh_token",
        indexes = {@Index(columnList = "user_id", name = "IDX_REFRESH_TOKEN_USER_ID")}
)
@Entity
public class RefreshToken {

    @Comment("리프레시 토큰값")
    @Id
    @Column(name = "token_value")
    @Length(min = 50, max = 50)
    private String value;

    @Comment("토큰 소유자 유저 아이디")
    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Comment("발급일시")
    @NotNull
    private LocalDateTime issuedDate;

    @Comment("만료일시")
    @NotNull
    private LocalDateTime expiredDate;

    public static RefreshToken create(String value, User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.value = value;
        refreshToken.user = user;
        refreshToken.issuedDate = LocalDateTime.now();
        refreshToken.updateExpiredDate();

        return refreshToken;
    }

    public static String makeValue() {
        int length = 50;
        int leftLimit = 48;
        int rightLimit = 122;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private void updateExpiredDate() {
        expiredDate = issuedDate.plusWeeks(2);
    }
}
