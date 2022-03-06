package com.jiwondev.account.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "ACCOUNT")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String nickname;

    private String password;

    private boolean emailVerified;

    private String emailCheckToken;
    private LocalDateTime emailCheckTokenGeneratedAt;

    private String bio; // biography, 개인 프로필 내용

    private String url; // 개인 홈페이지 url

    private String occupation; // 직업
    private String location; // 사는 곳
    /*
     * String 은 CLOB 으로 매핑된다.
     * profileImage 링크는 DB varchar(255)를 넘을 수 있기 때문에, @Lob 을 사용한다.
     *
     */
    @Lob
    @Basic(fetch = FetchType.EAGER)
    private String profileImage;
    @CreationTimestamp
    private LocalDateTime joinedAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private boolean studyCreatedByEmail;
    private boolean studyCreatedByWeb;
    private boolean studyEnrollmentResultByEmail;
    private boolean studyEnrollmentResultByWeb;
    private boolean studyUpdatedByEmail;
    private boolean studyUpdatedByWeb;

    @Builder
    private Account(Long id, String email, String nickname, String password,
        boolean studyCreatedByWeb,
        boolean studyEnrollmentResultByWeb, boolean studyUpdatedByWeb) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.studyCreatedByWeb = studyCreatedByWeb;
        this.studyEnrollmentResultByWeb = studyEnrollmentResultByWeb;
        this.studyUpdatedByWeb = studyUpdatedByWeb;
    }

    public void generateEmailCheckToken() {
        this.emailCheckToken = UUID.randomUUID().toString();
        this.emailCheckTokenGeneratedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(
            o)) {
            return false;
        }
        Account account = (Account) o;
        return id != null && Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
