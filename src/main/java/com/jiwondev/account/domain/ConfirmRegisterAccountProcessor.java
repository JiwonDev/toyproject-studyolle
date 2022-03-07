package com.jiwondev.account.domain;

import com.jiwondev.account.domain.exception.InvalidEmailException;
import com.jiwondev.account.domain.exception.InvalidEmailTokenException;

public final class ConfirmRegisterAccountProcessor {

    private final AccountReader accountReader;

    public ConfirmRegisterAccountProcessor(AccountReader accountReader) {
        this.accountReader = accountReader;
    }

    public void confirmRegister(String email, String token) {
        if (!accountReader.existsByEmail(email)) {
            throw new InvalidEmailException("유효하지 않은 계정입니다.");
        }

        var account = accountReader.findByEmail(email);

        if (!account.isValidEmailToken(token)) {
            throw new InvalidEmailTokenException("유효하지 않은 이메일 토큰값입니다.");
        }

        account.confirmEmail();
    }
}
