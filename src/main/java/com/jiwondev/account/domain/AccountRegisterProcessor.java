package com.jiwondev.account.domain;

import com.jiwondev.account.domain.dto.AccountRegisterCommand;
import org.springframework.security.crypto.password.PasswordEncoder;

public final class AccountRegisterProcessor {

    private final AccountStore accountStore;
    private final PasswordEncoder passwordEncoder;

    public AccountRegisterProcessor(AccountStore accountStore,
        PasswordEncoder passwordEncoder) {
        this.accountStore = accountStore;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(AccountRegisterCommand command) {
        var account = Account.builder()
            .email(command.getEmail())
            .nickname(command.getNickname())
            .password(passwordEncoder.encode(command.getPassword()))
            .studyCreatedByWeb(true)
            .studyEnrollmentResultByWeb(true)
            .studyUpdatedByWeb(true)
            .build();

        var newAccount = accountStore.save(account);
        newAccount.generateEmailCheckToken();
    }

}
