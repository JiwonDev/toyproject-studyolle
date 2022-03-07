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
    public long count() {
        return repository.count();
    }

    @Override
    public Account findByEmail(String email) {
        return repository.findByEmail(email)
            .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public boolean existsByNickname(String nickname) {
        return repository.existsByNickname(nickname);
    }
}
