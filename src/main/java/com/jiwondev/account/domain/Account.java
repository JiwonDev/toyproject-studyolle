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
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
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

    @Lob
    @Basic(fetch = FetchType.EAGER) // profileImage 링크는 DB varchar(255)를 넘을 수 있기 때문에, @Lob 을 사용한다.
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

    private Account(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;

    }

    public static Account createByWeb(String email, String nickname, String password) {
        var account = new Account(email, nickname, password);
        account.studyCreatedByWeb = true;
        account.studyEnrollmentResultByWeb = true;
        account.studyUpdatedByWeb = true;
        account.generateEmailCheckToken();
        return account;
    }

    public static Account of(String email, String nickname, String password) {
        var account = new Account(email, nickname, password);
        account.generateEmailCheckToken();
        return account;
    }

    private void generateEmailCheckToken() {
        this.emailCheckToken = UUID.randomUUID().toString();
        this.emailCheckTokenGeneratedAt = LocalDateTime.now();
    }

    public void confirmEmail() {
        this.emailVerified = true;
        this.joinedAt = LocalDateTime.now();
    }

    public boolean isValidEmailToken(String token) {
        return this.getEmailCheckToken().equals(token);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
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
