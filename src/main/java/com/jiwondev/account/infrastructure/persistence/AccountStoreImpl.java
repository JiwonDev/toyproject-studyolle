package com.jiwondev.account.infrastructure.persistence;

import com.jiwondev.account.domain.Account;
import com.jiwondev.account.domain.AccountStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class AccountStoreImpl implements AccountStore {

    private final JpaAccountRepository repository;

    @Override
    public Account save(Account account) {
        return repository.save(account);
    }
}
