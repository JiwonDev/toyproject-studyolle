package com.jiwondev.account.domain;

import com.jiwondev.account.domain.dto.AccountRegisterCommand;
import com.jiwondev.common.exception.IllegalStatusException;
import org.springframework.security.crypto.password.PasswordEncoder;

public final class RegisterAccountProcessor {

    private final AccountReader accountReader;
    private final AccountStore accountStore;
    private final PasswordEncoder passwordEncoder;

    public RegisterAccountProcessor(AccountReader accountReader,
        AccountStore accountStore,
        PasswordEncoder passwordEncoder) {
        this.accountReader = accountReader;
        this.accountStore = accountStore;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(AccountRegisterCommand command) {
        if (accountReader.existsByEmail(command.getEmail())) {
            throw new IllegalStatusException("회원가입 이메일 중복 검증이 되지 않았습니다.");
        }
        if (accountReader.existsByNickname(command.getNickname())) {
            throw new IllegalStatusException("회원가입 닉네임 중복 검증이 되지 않았습니다.");
        }

        var account = Account.createByWeb(
            command.getEmail(),
            command.getNickname(),
            passwordEncoder.encode(command.getPassword()));

        accountStore.save(account);
    }
}
