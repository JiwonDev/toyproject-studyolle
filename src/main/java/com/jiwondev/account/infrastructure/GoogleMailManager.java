package com.jiwondev.account.infrastructure;

import com.jiwondev.account.domain.Account;
import com.jiwondev.account.domain.AccountMailManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public final class GoogleMailManager implements AccountMailManager {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendConfirm(Account account) {
        // TODO Gmail 연동
        var mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("JiwonDev, 회원가입 인증");
        mailMessage.setText("/check-email-token?token=" + account.getEmailCheckToken() +
            "&email=" + account.getEmail());
        javaMailSender.send(mailMessage);
    }
}
