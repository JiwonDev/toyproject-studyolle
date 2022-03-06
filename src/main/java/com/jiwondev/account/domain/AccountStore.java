package com.jiwondev.account.domain;

import java.util.Optional;

public interface AccountStore {

    Optional<Account> save(Account account);
}
