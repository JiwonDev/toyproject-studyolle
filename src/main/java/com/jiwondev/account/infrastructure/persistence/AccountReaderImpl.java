package com.jiwondev.account.infrastructure.persistence;

import com.jiwondev.account.domain.Account;
import com.jiwondev.account.domain.AccountReader;
import com.jiwondev.common.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class AccountReaderImpl implements AccountReader {

    private final JpaAccountRepository repository;

    @Override
    public Account findByEmail(String email) {
        return repository.findByEmail(email)
            .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public boolean existByEmail(String email) {
        return false;
    }

    @Override
    public boolean existByNickname(String nickname) {
        return false;
    }
}
