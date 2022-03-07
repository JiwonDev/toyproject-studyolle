package com.jiwondev.account.application;

import com.jiwondev.account.domain.Account;
import com.jiwondev.account.domain.AccountMailManager;
import com.jiwondev.account.domain.AccountRegisterProcessor;
import com.jiwondev.account.interfaces.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountFacade {

    private final AccountRegisterProcessor accountRegisterProcessor;
    private final AccountMailManager accountMailManager;
    private final JavaMailSender javaMailSender;

    @Transactional
    public void register(SignUpForm form) {
        var account = Account.builder()
            .email(form.getEmail())
            .nickname(form.getNickname())
            .password(form.getPassword()) // TODO 패스워드 암호화
            .studyCreatedByWeb(true)
            .studyEnrollmentResultByWeb(true)
            .studyUpdatedByWeb(true)
            .build();

        accountRegisterProcessor.register(account);

        // TODO Mail 트랜잭션 분리
        accountMailManager.sendConfirm(account);

    }
}
