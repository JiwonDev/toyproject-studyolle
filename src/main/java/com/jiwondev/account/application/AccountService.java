package com.jiwondev.account.application;

import com.jiwondev.account.domain.Account;
import com.jiwondev.account.domain.AccountStore;
import com.jiwondev.account.domain.event.RegisterAccountEvent;
import com.jiwondev.account.interfaces.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountStore accountStore;
    private final AccountMapper mapper;

    @Transactional
    public void register(SignUpForm form) {
        var account = accountStore.save(
            Account.builder()
                .email(form.getEmail())
                .nickname(form.getNickname())
                .password(form.getPassword()) // TODO 패스워드 암호화
                .studyCreatedByWeb(true)
                .studyEnrollmentResultByWeb(true)
                .studyUpdatedByWeb(true)
                .build()
        ).orElseThrow(IllegalStateException::new);

        account.generateEmailCheckToken();

        var mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("JiwonDev, 회원가입 인증");
        mailMessage.setText("/check-email-token?token=" + account.getEmailCheckToken() +
            "&email=" + account.getEmail());
    }
}
