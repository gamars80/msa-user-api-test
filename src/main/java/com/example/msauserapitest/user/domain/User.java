package com.example.msauserapitest.user.domain;

import com.example.msauserapitest.user.enums.RoleType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "user",
        indexes = {@Index(columnList = "phone_number", name = "IDX_USER_PHONE_NUMBER")}
)
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Where(clause = "deleted=0")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", columnDefinition = "bigint")
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

    @Comment("핸드폰 번호")
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Comment("이름")
    @Column(name = "name", length = 32, nullable = false)
    private String name;

    @Comment("역할 유형")
    @Column(name = "role_type", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public static User create(String phoneNumber, String name, RoleType roleType) {
        return User.builder()
                .phoneNumber(phoneNumber)
                .name(name)
                .roleType(roleType)
                .build();
    }

    public void lostManagerRole() {
        this.roleType = RoleType.CUSTOMER;
    }

    public void giveSuperManagerRole() {
        this.roleType = RoleType.SUPER_ADMIN;
    }

    public Boolean isManager() {
        return (this.roleType == RoleType.ADMIN || this.roleType == RoleType.SUPER_ADMIN);
    }

    public void delete() {
        this.deleted = true;
        this.phoneNumber = this.phoneNumber + "-" + this.getId();
    }

    public void updatePhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void updateName(String name) {
        this.name = name;
    }
}
